val reactJS         = "17.0.2"
val copyToClipboard = "3.3.1"
val scalaJsReact    = "2.0.0-RC3"

Global / onChangedBuildSource := ReloadOnSourceChanges

Global / resolvers += Resolver.sonatypeRepo("public")

inThisBuild(
  List(
    homepage := Some(url("https://github.com/cquiroz/scalajs-react-clipboard")),
    licenses := Seq(
      "BSD 3-Clause License" -> url(
        "https://opensource.org/licenses/BSD-3-Clause"
      )
    ),
    developers := List(
      Developer(
        "cquiroz",
        "Carlos Quiroz",
        "carlos.m.quiroz@gmail.com",
        url("https://github.com/cquiroz")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/cquiroz/scalajs-react-clipboard"),
        "scm:git:git@github.com:cquiroz/scalajs-react-clipboard.git"
      )
    )
  )
)

val root =
  project
    .in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade)
    .settings(
      name := "root",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "react-clipboard",
      Compile / npmDependencies ++= Seq(
        "react"             -> reactJS,
        "react-dom"         -> reactJS,
        "copy-to-clipboard" -> copyToClipboard
      ),
      // Requires the DOM for tests
      Test / requireJsDomEnv := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      webpack / version := "4.44.1",
      webpackCliVersion / version := "3.3.11",
      startWebpackDevServer / version := "3.11.0",
      Test / webpackConfigFile := Some(
        baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
      ),
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      Test / scalaJSStage := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"      % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test"      % scalaJsReact % Test,
        "io.github.cquiroz.react"           %%% "common"    % "0.13.0",
        "io.github.cquiroz.react"           %%% "test"      % "0.13.0"     % Test,
        "org.scalameta"                     %%% "munit"     % "0.7.28"     % Test,
        "org.typelevel"                     %%% "cats-core" % "2.6.1"      % Test
      ),
      testFrameworks += new TestFramework("munit.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := "2.13.5",
  organization := "io.github.cquiroz.react",
  sonatypeProfileName := "io.github.cquiroz",
  description := "scala.js facade for react-copy-to-clipboard",
  homepage := Some(url("https://github.com/cquiroz/scalajs-react-clipboard"))
)
