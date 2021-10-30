name := "scalajs-react-virtuoso"

Global / onChangedBuildSource := ReloadOnSourceChanges

Global / excludeLintKeys += webpackExtraArgs

Global / semanticdbEnabled := true

val reactVirtuoso      = "2.2.2"
val scalaJsReact       = "2.0.0-RC3"
val reactJS            = "17.0.2"
val scalaJsReactCommon = "0.14.5"
val munit              = "0.7.29"

addCommandAlias(
  "restartWDS",
  "; demo / Compile / fastOptJS / stopWebpackDevServer; demo / Compile / fastOptJS / startWebpackDevServer"
)

addCommandAlias(
  "fixImports",
  "; scalafix OrganizeImports"
)

inThisBuild(
  List(
    scalaVersion        := "2.13.6",
    organization        := "io.github.toddburnside",
    sonatypeProfileName := "io.github.toddburnside",
    homepage            := Some(
      url("https://github.com/toddburnside/scalajs-react-virtuoso")
    ),
    licenses            := Seq(
      "BSD 3-Clause License" -> url(
        "https://opensource.org/licenses/BSD-3-Clause"
      )
    ),
    developers          := List(
      Developer(
        "toddburnside ",
        "Todd Burnside",
        "toddburnside@gmail.com",
        url("https://github.com/toddburnside")
      ),
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
    scmInfo             := Some(
      ScmInfo(
        url("https://github.com/toddburnside/scalajs-react-virtuoso"),
        "scm:git:git@github.com:toddburnside/scalajs-react-virtuoso.git"
      )
    )
  )
)

val root =
  project
    .in(file("."))
    .aggregate(facade, demo)
    .settings(
      name            := "scalajs-react-virtuoso",
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
      webpackExtraArgs                      := Seq("--progress"),
      useYarn                               := true,
      fastOptJS / webpackBundlingMode       := BundlingMode.LibraryOnly(),
      fullOptJS / webpackBundlingMode       := BundlingMode.Application,
      test                                  := {},
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
        "react"          -> reactJS,
        "react-dom"      -> reactJS,
        "react-virtuoso" -> reactVirtuoso
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
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      name                            := "facade",
      moduleName                      := "scalajs-react-virtuoso",
      Test / publishArtifact          := false,
      // Requires the DOM for tests
      Test / requireJsDomEnv          := true,
      // Use yarn as it is faster than npm
      useYarn                         := true,
      yarnExtraArgs                   := {
        if (insideCI.value) List("--frozen-lockfile") else List.empty
      },
      webpack / version               := "4.44.1",
      installJsdom / version          := "16.4.0",
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      Test / scalaJSStage             := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"   % scalaJsReact,
        "io.github.cquiroz.react"           %%% "common" % scalaJsReactCommon,
        "com.github.japgolly.scalajs-react" %%% "test"   % scalaJsReact % Test,
        "org.scalameta"                     %%% "munit"  % munit        % Test
      ),
      Compile / npmDependencies ++= Seq(
        "react"          -> reactJS,
        "react-dom"      -> reactJS,
        "react-virtuoso" -> reactVirtuoso
      ),
      scalacOptions ~= (_.filterNot(
        Set(
          // By necessity facades will have unused params
          "-Wdead-code",
          "-Wunused:params",
          "-Wunused:explicits",
          "-Wunused:imports",
          "-Wvalue-discard",
          "-Xlint:missing-interpolator"
        )
      )),
      // Some Scalablytyped generated Scaladocs are malformed.
      // Workaround: https://github.com/xerial/sbt-sonatype/issues/30#issuecomment-342532067
      // Compile / doc / sources := Seq(),
      Test / webpackConfigFile        := Some(
        baseDirectory.value / "test.webpack.config.js"
      ),
      testFrameworks += new TestFramework("munit.Framework")
    )
