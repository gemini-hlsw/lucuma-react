name := "scalajs-react-beautiful-dnd"

Global / onChangedBuildSource := ReloadOnSourceChanges

val reactJS          = "17.0.2"
val scalaJsReact     = "2.0.0-RC4"
val reactBeautiulDnD = "13.1.0"
val atlasKitTree     = "8.2.0"
val cats             = "2.6.1" // Only used in demo

addCommandAlias(
  "restartDemoWDS",
  "; demo/fastOptJS/stopWebpackDevServer; demo/fastOptJS/startWebpackDevServer"
)

addCommandAlias(
  "restartTreeDemoWDS",
  "; treeDemo/fastOptJS/stopWebpackDevServer; treeDemo/fastOptJS/startWebpackDevServer"
)

inThisBuild(
  List(
    scalaVersion             := "2.13.6",
    Test / parallelExecution := false,
    organization             := "com.rpiaggio",
    sonatypeProfileName      := "com.rpiaggio",
    homepage                 := Some(
      url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd")
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
        url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd"),
        "scm:git:git@github.com:rpiaggio/scalajs-react-beautiful-dnd.git"
      )
    )
  )
)

lazy val commonSettings = Seq(
  description            := "scala.js facade for react-beautiful-dnd",
  Test / publishArtifact := false,
  publishMavenStyle      := true,
  scalacOptions ~= (_.filterNot(
    Set(
      // By necessity facades will have unused params
      "-Wdead-code",
      "-Wunused:params",
      "-Wunused:explicits",
      "-Wunused:imports"
    )
  ))
)

val root =
  project
    .in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade, demo, treeFacade, treeDemo)
    .settings(
      name            := "root",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish         := {},
      publishLocal    := {},
      publishArtifact := false,
      Keys.`package`  := file("")
    )

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name                            := "facade",
      moduleName                      := "scalajs-react-beautiful-dnd",
      Compile / npmDependencies ++= Seq(
        "react"               -> reactJS,
        "react-dom"           -> reactJS,
        "react-beautiful-dnd" -> reactBeautiulDnD
      ),
      // Use yarn as it is faster than npm
      useYarn                         := true,
      webpack / version               := "4.30.0",
      startWebpackDevServer / version := "3.3.1",
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
      webpackBundlingMode             := BundlingMode.LibraryOnly(),
      webpackDevServerExtraArgs       := Seq("--inline"),
      fastOptJS / webpackConfigFile   := Some(
        baseDirectory.value / "dev.webpack.config.js"
      ),
      webpack / version               := "4.30.0",
      startWebpackDevServer / version := "3.3.1",
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "cats-core" % cats
      ),
      // don't publish the demo
      publish                         := {},
      publishLocal                    := {},
      publishArtifact                 := false,
      Keys.`package`                  := file(""),
      Compile / npmDevDependencies ++= Seq(
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
      name                            := "tree-facade",
      moduleName                      := "scalajs-react-atlaskit-tree",
      Compile / npmDependencies ++= Seq(
        "@atlaskit/tree" -> atlasKitTree
      ),
      // Use yarn as it is faster than npm
      useYarn                         := true,
      webpack / version               := "4.30.0",
      startWebpackDevServer / version := "3.3.1",
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
      webpackBundlingMode             := BundlingMode.LibraryOnly(),
      webpackDevServerExtraArgs       := Seq("--inline"),
      fastOptJS / webpackConfigFile   := Some(
        baseDirectory.value / "dev.webpack.config.js"
      ),
      webpack / version               := "4.30.0",
      startWebpackDevServer / version := "3.3.1",
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "cats-core" % cats
      ),
      // don't publish the demo
      publish                         := {},
      publishLocal                    := {},
      publishArtifact                 := false,
      Keys.`package`                  := file(""),
      Compile / npmDevDependencies ++= Seq(
        "css-loader"   -> "1.0.0",
        "style-loader" -> "0.23.0"
      ),
      Compile / fastOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      Compile / fullOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) }
    )
    .dependsOn(treeFacade)
