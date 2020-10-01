import sbt._

val reactJS      = "16.13.1"
val scalaJsReact = "1.7.5"

parallelExecution in (ThisBuild, Test) := false

ThisBuild / turbo := true

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    homepage := Some(url("https://github.com/cquiroz/scalajs-react-common")),
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
        url("https://github.com/cquiroz/scalajs-react-common"),
        "scm:git:git@github.com:cquiroz/scalajs-react-common.git"
      )
    )
  )
)

lazy val common: Project =
  project
    .in(file("common"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "common"
    )

lazy val cats: Project =
  project
    .in(file("cats"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "cats",
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "cats-core"        % "2.2.0",
        "org.typelevel" %%% "cats-testkit"     % "2.2.0"  % Test,
        "org.scalameta" %%% "munit"            % "0.7.13" % Test,
        "org.typelevel" %%% "discipline-munit" % "0.3.0"  % Test
      ),
      scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule)),
      testFrameworks += new TestFramework("munit.Framework")
    )
    .dependsOn(common)

lazy val test =
  project
    .in(file("test"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "test",
      npmDependencies in Compile ++= Seq(
        "react"     -> reactJS,
        "react-dom" -> reactJS
      ),
      // Requires the DOM for tests
      requireJsDomEnv in Test := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      version in webpack := "4.44.1",
      version in webpackCliVersion := "3.3.11",
      // Compile tests to JS using fast-optimisation
      scalaJSStage in Test := FastOptStage,
      libraryDependencies ++= Seq(
        "org.scalameta" %%% "munit"     % "0.7.13",
        "org.typelevel" %%% "cats-core" % "2.2.0" % Test
      ),
      webpackExtraArgs in Test := Seq("--verbose", "--progress", "true"),
      webpackConfigFile in Test := Some(
        baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
      ),
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
      testFrameworks += new TestFramework("munit.Framework")
    )
    .dependsOn(cats, common)

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "scalajs-react-common",
    // No, SBT, we don't want any artifacts for root.
    // No, not even an empty jar.
    publish := {},
    publishLocal := {},
    publishArtifact := false,
    Keys.`package` := file("")
  )
  .aggregate(common, cats, test)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.3",
  organization := "io.github.cquiroz.react",
  description := "scala.js react common utilities",
  sonatypeProfileName := "io.github.cquiroz",
  publishArtifact in Test := false,
  libraryDependencies ++= Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % scalaJsReact,
    "com.github.japgolly.scalajs-react" %%% "test" % scalaJsReact % "test"
  )
)
