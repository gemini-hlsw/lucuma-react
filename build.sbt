name := "scalajs-react-datepicker"

Global / onChangedBuildSource := ReloadOnSourceChanges

val reactJS              = "17.0.2"
val scalaJsReact         = "2.0.0-RC2"
val reactDatePicker      = "4.1.1"
val reactDatePickerTypes = "3.1.8"

addCommandAlias(
  "restartWDS",
  "; demo/fastOptJS/stopWebpackDevServer; demo/fastOptJS/startWebpackDevServer"
)

inThisBuild(
  List(
    scalaVersion                         := "2.13.7",
    ThisBuild / Test / parallelExecution := false,
    organization                         := "com.rpiaggio",
    sonatypeProfileName                  := "com.rpiaggio",
    homepage                             := Some(
      url("https://github.com/rpiaggio/scalajs-react-datepicker")
    ),
    licenses                             := Seq(
      "BSD 3-Clause License" -> url(
        "https://opensource.org/licenses/BSD-3-Clause"
      )
    ),
    developers                           := List(
      Developer(
        "rpiaggio",
        "Raúl Piaggio",
        "rpiaggio@gmail.com",
        url("https://github.com/rpiaggio")
      ),
      Developer(
        "cquiroz",
        "Carlos Quiroz",
        "carlos.m.quiroz@gmail.com",
        url("https://github.com/cquiroz")
      )
    ),
    scmInfo                              := Some(
      ScmInfo(
        url("https://github.com/rpiaggio/scalajs-react-datepicker"),
        "scm:git:git@github.com:rpiaggio/scalajs-react-datepicker.git"
      )
    )
  )
)

val root =
  project
    .in(file("."))
    .aggregate(facade, demo)
    .settings(
      name            := "scalajs-react-datepicker",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish         := {},
      publishLocal    := {},
      publishArtifact := false,
      Keys.`package`  := file("")
    )

lazy val demo =
  project
    .in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      webpack / version                     := "4.44.1",
      startWebpackDevServer / version       := "3.3.1",
      fastOptJS / webpackConfigFile         := Some(
        baseDirectory.value / "webpack" / "dev.webpack.config.js"
      ),
      fullOptJS / webpackConfigFile         := Some(
        baseDirectory.value / "webpack" / "prod.webpack.config.js"
      ),
      webpackMonitoredDirectories += (Compile / resourceDirectory).value,
      webpackResources                      := (baseDirectory.value / "webpack") * "*.js",
      webpackMonitoredFiles / includeFilter := "*",
      useYarn                               := true,
      fastOptJS / webpackBundlingMode       := BundlingMode.LibraryOnly(),
      fullOptJS / webpackBundlingMode       := BundlingMode.Application,
      test                                  := {},
      Compile / fastOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      Compile / fullOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      // NPM libs for development, mostly to let webpack do its magic
      Compile / npmDevDependencies ++= Seq(
        "postcss-loader"                     -> "3.0.0",
        "autoprefixer"                       -> "9.4.4",
        "url-loader"                         -> "1.1.1",
        "file-loader"                        -> "3.0.1",
        "css-loader"                         -> "2.1.0",
        "style-loader"                       -> "0.23.1",
        "less"                               -> "3.9.0",
        "less-loader"                        -> "4.1.0",
        "sass"                               -> "1.26.10",
        "sass-loader"                        -> "9.0.2",
        "webpack-merge"                      -> "4.2.1",
        "mini-css-extract-plugin"            -> "0.5.0",
        "webpack-dev-server-status-bar"      -> "1.1.0",
        "cssnano"                            -> "4.1.8",
        "uglifyjs-webpack-plugin"            -> "2.1.1",
        "html-webpack-plugin"                -> "3.2.0",
        "optimize-css-assets-webpack-plugin" -> "5.0.1",
        "favicons-webpack-plugin"            -> "0.0.9"
      ),
      Compile / npmDependencies ++= Seq(
        "react"            -> reactJS,
        "react-dom"        -> reactJS,
        "react-datepicker" -> reactDatePicker
      ),
      libraryDependencies ++= Seq(
        "io.github.cquiroz" %%% "scala-java-time" % "2.3.0"
      ),
      // don't publish the demo
      publish                               := {},
      publishLocal                          := {},
      publishArtifact                       := false,
      Keys.`package`                        := file("")
    )
    .dependsOn(facade)

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
    .settings(
      name                                                := "facade",
      moduleName                                          := "scalajs-react-datepicker",
      // Requires the DOM for tests
      Test / requireJsDomEnv                              := true,
      // Use yarn as it is faster than npm
      useYarn                                             := true,
      yarnExtraArgs                                       := {
        if (insideCI.value) List("--pure-lockfile") else List.empty
      },
      webpack / version                                   := "4.44.1",
      installJsdom / version                              := "16.4.0",
      scalaJSUseMainModuleInitializer                     := false,
      // Compile tests to JS using fast-optimisation
      Test / scalaJSStage                                 := FastOptStage,
      Compile / npmDependencies ++= Seq(
        "react"                   -> reactJS,
        "react-dom"               -> reactJS,
        "react-datepicker"        -> reactDatePicker,
        "@types/react-datepicker" -> reactDatePickerTypes
      ),
      stUseScalaJsDom                                     := true,
      stOutputPackage                                     := "lucuma",
      stFlavour                                           := Flavour.ScalajsReact,
      stIgnore ++= List("react-dom"),
      (Compile / stMinimize).withRank(KeyRanks.Invisible) :=
        Selection.AllExcept("@types/react-datepicker"),
      // stEnableScalaJsDefined := Selection.All,
      scalacOptions ~= (_.filterNot(
        Set(
          // By necessity facades will have unused params
          "-Wdead-code",
          "-Wunused:params",
          "-Wunused:explicits",
          "-Wunused:imports",
          "-Wvalue-discard"
        )
      )),
      // Some Scalablytyped generated Scaladocs are malformed.
      // Workaround: https://github.com/xerial/sbt-sonatype/issues/30#issuecomment-342532067
      Compile / doc / sources                             := Seq()
    )
