name := "scalajs-react-beautiful-dnd"

scalaVersion in ThisBuild := "2.13.2"

Global / onChangedBuildSource := IgnoreSourceChanges

val reactJS           = "16.8.6"
val resactBeautiulDnD = "13.0.0"
val scalaJsReact      = "1.6.0"
val cats              = "2.1.1" // Only used in demo

parallelExecution in (ThisBuild, Test) := false

addCommandAlias(
  "restartWDS",
  "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

inThisBuild(
  List(
    homepage := Some(
      url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd")
    ),
    licenses := Seq(
      "BSD 3-Clause License" -> url(
        "https://opensource.org/licenses/BSD-3-Clause"
      )
    ),
    developers := List(
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
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd"),
        "scm:git:git@github.com:rpiaggio/scalajs-react-beautiful-dnd.git"
      )
    )
  )
)

lazy val commonSettings = Seq(
  scalaVersion := scalaVersion.value,
  organization := "io.github.rpiaggio.react",
  sonatypeProfileName := "io.github.rpiaggio",
  description := "scala.js facade for react-beautiful-dnd",
  homepage := Some(
    url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd")
  ),
  licenses := Seq(
    "BSD 3-Clause License" -> url(
      "https://opensource.org/licenses/BSD-3-Clause"
    )
  ),
  publishArtifact in Test := false,
  publishMavenStyle := true
)

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

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "facade",
      moduleName := "scalajs-react-beautiful-dnd",
      npmDependencies in Compile ++= Seq(
        "react"               -> reactJS,
        "react-dom"           -> reactJS,
        "react-beautiful-dnd" -> resactBeautiulDnD
      ),
      // Use yarn as it is faster than npm
      useYarn := true,
      version in webpack := "4.30.0",
      version in webpackCliVersion := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
      scalaJSUseMainModuleInitializer := false,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % scalaJsReact
      )
    )

lazy val demo =
  project
    .in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      scalaJSUseMainModuleInitializer := true,
      webpackBundlingMode := BundlingMode.LibraryOnly(),
      webpackDevServerExtraArgs := Seq("--inline"),
      webpackConfigFile in fastOptJS := Some(
        baseDirectory.value / "dev.webpack.config.js"
      ),
      version in webpack := "4.30.0",
      version in webpackCliVersion := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "cats-core" % cats
      ),
      // don't publish the demo
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file(""),
      npmDevDependencies in Compile ++= Seq(
        "css-loader"   -> "1.0.0",
        "style-loader" -> "0.23.0"
      )
    )
    .dependsOn(facade)
