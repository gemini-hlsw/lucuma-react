name := "scalajs-react-beautiful-dnd"

scalaVersion in ThisBuild := "2.13.2"

Global / onChangedBuildSource := ReloadOnSourceChanges

val reactJS          = "16.13.1"
val reactBeautiulDnD = "13.0.0"
val atlasKitTree     = "7.1.2"
val scalaJsReact     = "1.7.3"
val cats             = "2.1.1" // Only used in demo

parallelExecution in (ThisBuild, Test) := false

addCommandAlias(
  "restartDemoWDS",
  "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

addCommandAlias(
  "restartTreeDemoWDS",
  "; treeDemo/fastOptJS::stopWebpackDevServer; treeDemo/fastOptJS::startWebpackDevServer"
)

inThisBuild(
  List(
    scalaVersion := scalaVersion.value,
    organization := "com.rpiaggio",
    sonatypeProfileName := "com.rpiaggio",
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
  description := "scala.js facade for react-beautiful-dnd",
  publishArtifact in Test := false,
  publishMavenStyle := true,
  scalacOptions ~= (_.filterNot(
    Set(
      // By necessity facades will have unused params
      "-Wdead-code",
      "-Wunused:params"
    )
  ))
)

val root =
  project
    .in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade, demo, treeFacade, treeDemo)
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
        "react-beautiful-dnd" -> reactBeautiulDnD
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

lazy val treeFacade =
  project
    .in(file("tree-facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "tree-facade",
      moduleName := "scalajs-react-atlaskit-tree",
      npmDependencies in Compile ++= Seq(
        "@atlaskit/tree" -> atlasKitTree
      ),
      // Use yarn as it is faster than npm
      useYarn := true,
      version in webpack := "4.30.0",
      version in webpackCliVersion := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
      scalaJSUseMainModuleInitializer := false
    )
    .dependsOn(facade)

lazy val treeDemo =
  project
    .in(file("tree-demo"))
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
      ),
      scalaJSLinkerConfig in (Compile, fastOptJS) ~= { _.withSourceMap(false) },
      scalaJSLinkerConfig in (Compile, fullOptJS) ~= { _.withSourceMap(false) }
    )
    .dependsOn(treeFacade)
