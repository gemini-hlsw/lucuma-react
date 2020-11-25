val reactJS         = "16.13.1"
val scalaJsReact    = "1.7.7"
val reactGridLayout = "1.0.0"
val scalaJSDom      = "1.1.0"

parallelExecution in (ThisBuild, Test) := false

cancelable in Global := true

Global / onChangedBuildSource := ReloadOnSourceChanges

resolvers in Global += Resolver.sonatypeRepo("public")

addCommandAlias("restartWDS",
                "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

inThisBuild(
  List(
    homepage := Some(url("https://github.com/cquiroz/scalajs-react-grid-layout")),
    licenses := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
    developers := List(
      Developer("cquiroz",
                "Carlos Quiroz",
                "carlos.m.quiroz@gmail.com",
                url("https://github.com/cquiroz")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/cquiroz/scalajs-react-grid-layout"),
        "scm:git:git@github.com:cquiroz/scalajs-react-grid-layout.git"
      )
    )
  )
)

resolvers in Global += Resolver.sonatypeRepo("releases")

val root =
  project
    .in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade, demo)
    .settings(
      name := "root",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )

lazy val demo =
  project
    .in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      version in webpack := "4.32.0",
      version in startWebpackDevServer := "3.3.1",
      webpackConfigFile in fastOptJS := Some(
        baseDirectory.value / "src" / "webpack" / "webpack-dev.config.js"
      ),
      webpackConfigFile in fullOptJS := Some(
        baseDirectory.value / "src" / "webpack" / "webpack-prod.config.js"
      ),
      webpackMonitoredDirectories += (resourceDirectory in Compile).value,
      webpackResources := (baseDirectory.value / "src" / "webpack") * "*.js",
      includeFilter in webpackMonitoredFiles := "*",
      useYarn := true,
      webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly(),
      webpackBundlingMode in fullOptJS := BundlingMode.Application,
      test := {},
      webpackDevServerPort := 6060,
      npmDevDependencies in Compile ++= Seq(
        "css-loader"                         -> "0.28.11",
        "less"                               -> "2.3.1",
        "less-loader"                        -> "4.1.0",
        "mini-css-extract-plugin"            -> "0.4.0",
        "html-webpack-plugin"                -> "3.2.0",
        "url-loader"                         -> "1.0.1",
        "style-loader"                       -> "0.21.0",
        "postcss-loader"                     -> "2.1.5",
        "cssnano"                            -> "3.10.0",
        "optimize-css-assets-webpack-plugin" -> "4.0.1",
        "webpack-merge"                      -> "4.1.0",
        "webpack-dev-server-status-bar"      -> "1.0.0"
      ),
    npmDependencies in Compile ++= Seq(
        "react"             -> reactJS,
        "react-dom"         -> reactJS,
        "react-grid-layout" -> reactGridLayout
      ),
      libraryDependencies +=
        "io.github.cquiroz.react" %%% "react-sizeme" % "0.6.1",
      // don't publish the demo
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )
    .dependsOn(facade)

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "react-grid-layout",
      version in webpack := "4.44.1",
      version in startWebpackDevServer := "3.3.1",
      // Requires the DOM for tests
      requireJsDomEnv in Test := true,
      // Compile tests to JS using fast-optimisation
      // scalaJSStage in Test            := FastOptStage,
      npmDependencies in Compile ++= Seq(
        "react"             -> reactJS,
        "react-dom"         -> reactJS,
        "react-grid-layout" -> reactGridLayout
      ),
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"        % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "extra"       % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test"        % scalaJsReact % Test,
        "org.scala-js"                      %%% "scalajs-dom" % scalaJSDom,
        "io.github.cquiroz.react"           %%% "common"      % "0.11.1",
        "com.lihaoyi"                       %%% "utest"       % "0.7.5"      % Test,
        "org.typelevel"                     %%% "cats-core"   % "2.2.0"      % Test
      ),
      webpackConfigFile in Test := Some(
        baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := "2.13.3",
  organization := "io.github.cquiroz.react",
  sonatypeProfileName := "io.github.cquiroz",
  description := "scala.js facade for react-grid-layout ",
  publishArtifact in Test := false,
  scalacOptions ~= (_.filterNot(
    Set(
      // By necessity facades will have unused params
      "-Wdead-code"
    )
  ))
)
