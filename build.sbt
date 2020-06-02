val reactJS         = "16.13.1"
val copyToClipboard = "3.2.0"
val scalaJsReact    = "1.7.0"

parallelExecution in (ThisBuild, Test) := false

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
      npmDependencies in Compile ++= Seq(
        "react"             -> reactJS,
        "react-dom"         -> reactJS,
        "copy-to-clipboard" -> copyToClipboard
      ),
      // Requires the DOM for tests
      requireJsDomEnv in Test := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      version in webpack := "4.20.2",
      version in startWebpackDevServer := "3.1.8",
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      scalaJSStage in Test := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"      % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test"      % scalaJsReact % Test,
        "io.github.cquiroz.react"           %%% "common"    % "0.9.1",
        "io.github.cquiroz.react"           %%% "test"      % "0.9.1"      % Test,
        "com.lihaoyi"                       %%% "utest"     % "0.7.4"      % Test,
        "org.typelevel"                     %%% "cats-core" % "2.1.1"      % Test
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := "2.13.2",
  organization := "io.github.cquiroz.react",
  sonatypeProfileName := "io.github.cquiroz",
  description := "scala.js facade for react-copy-to-clipboard",
  homepage := Some(url("https://github.com/cquiroz/scalajs-react-clipboard"))
)
