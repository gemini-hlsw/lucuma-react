name := "scalajs-react-highcharts"

Global / onChangedBuildSource := ReloadOnSourceChanges

val reactJS            = "17.0.2"
val scalaJsReact       = "2.0.0-RC3"
val scalaJsReactCommon = "0.14.3"
val highcharts         = "9.1.2"

addCommandAlias(
  "restartWDS",
  "; demo/fastOptJS/stopWebpackDevServer; demo/fastOptJS/startWebpackDevServer"
)

inThisBuild(
  List(
    scalaVersion             := "2.13.6",
    Test / parallelExecution := false,
    organization             := "com.rpiaggio",
    sonatypeProfileName      := "com.rpiaggio",
    homepage                 := Some(
      url("https://github.com/rpiaggio/scalajs-react-highcharts")
    ),
    licenses                 := Seq(
      "BSD 3-Clause License" -> url(
        "https://opensource.org/licenses/BSD-3-Clause"
      )
    ),
    developers               := List(
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
    scmInfo                  := Some(
      ScmInfo(
        url("https://github.com/rpiaggio/scalajs-react-highcharts"),
        "scm:git:git@github.com:rpiaggio/scalajs-react-highcharts.git"
      )
    )
  )
)

val root                =
  project
    .in(file("."))
    .aggregate(facade, demo)
    .settings(
      name            := "scalajs-react-highcharts",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish         := {},
      publishLocal    := {},
      publishArtifact := false,
      Keys.`package`  := file("")
    )

lazy val demo           =
  project
    .in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      webpack / version                     := "4.32.0",
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
        "webpack-merge"                      -> "4.2.1",
        "mini-css-extract-plugin"            -> "0.5.0",
        "webpack-dev-server-status-bar"      -> "1.1.0",
        "cssnano"                            -> "4.1.8",
        "uglifyjs-webpack-plugin"            -> "2.1.1",
        "html-webpack-plugin"                -> "3.2.0",
        "optimize-css-assets-webpack-plugin" -> "5.0.1",
        "favicons-webpack-plugin"            -> "0.0.9",
        "why-did-you-update"                 -> "1.0.6"
      ),
      Compile / npmDependencies ++= Seq(
        "react"      -> reactJS,
        "react-dom"  -> reactJS,
        "highcharts" -> highcharts
      ),
      // don't publish the demo
      publish                               := {},
      publishLocal                          := {},
      publishArtifact                       := false,
      Keys.`package`                        := file("")
    )
    .dependsOn(facade)

lazy val facade         =
  project
    .in(file("facade"))
    .settings(commonSettings: _*)
    .enablePlugins(ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
    .settings(
      name                                                := "facade",
      moduleName                                          := "scalajs-react-highcharts",
      // Requires the DOM for tests
      Test / requireJsDomEnv                              := true,
      // Use yarn as it is faster than npm
      useYarn                                             := true,
      yarnExtraArgs                                       := {
        if (insideCI.value) List("--pure-lockfile") else List.empty
      },
      webpack / version                                   := "4.32.0",
      installJsdom / version                              := "15.2.1",
      scalaJSUseMainModuleInitializer                     := false,
      // Compile tests to JS using fast-optimisation
      Test / scalaJSStage                                 := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"   % scalaJsReact,
        "io.github.cquiroz.react"           %%% "common" % scalaJsReactCommon,
        "com.github.japgolly.scalajs-react" %%% "test"   % scalaJsReact % Test,
        "com.lihaoyi"                       %%% "utest"  % "0.7.10"     % Test
      ),
      Compile / npmDependencies ++= Seq(
        "react"      -> reactJS,
        "react-dom"  -> reactJS,
        "highcharts" -> highcharts
      ),
      stIgnore ++= List("react", "react-dom"),
      stUseScalaJsDom                                     := true,
      stOutputPackage                                     := "gpp",
      (Compile / stMinimize).withRank(KeyRanks.Invisible) := Selection.AllExcept("highcharts"),
      stTypescriptVersion                                 := "4.2.4",
      // stEnableScalaJsDefined := Selection.All,
      scalacOptions ~= (_.filterNot(
        Set(
          // By necessity facades will have unused params
          "-Wdead-code",
          "-Wunused:params",
          "-Wunused:explicits",
          "-Wunused:imports"
        )
      )),
      // Some Scalablytyped generated Scaladocs are malformed.
      // Workaround: https://github.com/xerial/sbt-sonatype/issues/30#issuecomment-342532067
      Compile / doc / sources                             := Seq(),
      Test / webpackConfigFile                            := Some(
        baseDirectory.value / "test.webpack.config.js"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := scalaVersion.value,
  scalacOptions ~= (_.filterNot(
    Set(
      // By necessity facades will have unused params
      "-Wdead-code",
      "-Wunused:params",
      "-Wunused:explicits"
    )
  ))
)
