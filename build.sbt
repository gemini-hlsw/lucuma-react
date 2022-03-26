import org.scalajs.linker.interface.ModuleSplitStyle

val reactJS         = "17.0.2"
val reactGridLayout = "1.2.5"

val scalaJsReact = "2.0.1"
val scalaJSDom   = "2.1.0"
val reactCommon  = "0.16.0"
val cats         = "2.7.0"
val utest        = "0.7.11"

Global / onChangedBuildSource := ReloadOnSourceChanges

Global / resolvers += Resolver.sonatypeRepo("public")
Global / resolvers += Resolver.sonatypeRepo("releases")

addCommandAlias("restartWDS",
                "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

inThisBuild(
  List(
    homepage   := Some(url("https://github.com/cquiroz/scalajs-react-grid-layout")),
    licenses   := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
    developers := List(
      Developer("cquiroz",
                "Carlos Quiroz",
                "carlos.m.quiroz@gmail.com",
                url("https://github.com/cquiroz")
      )
    ),
    scmInfo    := Some(
      ScmInfo(
        url("https://github.com/cquiroz/scalajs-react-grid-layout"),
        "scm:git:git@github.com:cquiroz/scalajs-react-grid-layout.git"
      )
    )
  )
)

val root =
  project
    .in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade, demo)
    .settings(
      name            := "root",
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
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings: _*)
    .settings(
      test                                  := {},
      Compile / fastLinkJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      Compile / fullLinkJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
      Compile / fastLinkJS / scalaJSLinkerConfig ~= (_.withModuleSplitStyle(
        ModuleSplitStyle.SmallestModules
      )),
      Compile / fullLinkJS / scalaJSLinkerConfig ~= (_.withModuleSplitStyle(
        ModuleSplitStyle.FewestModules
      )),
      publish / skip                        := true,
      libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.7.1",
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
    .settings(commonSettings: _*)
    .settings(
      name                            := "react-grid-layout",
      webpack / version               := "4.44.1",
      startWebpackDevServer / version := "3.3.1",
      // Requires the DOM for tests
      Test / requireJsDomEnv          := true,
      // Compile tests to JS using fast-optimisation
      // scalaJSStage in Test            := FastOptStage,
      Compile / npmDependencies ++= Seq(
        "react"             -> reactJS,
        "react-dom"         -> reactJS,
        "react-grid-layout" -> reactGridLayout
      ),
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"        % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "extra"       % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test"        % scalaJsReact % Test,
        "org.scala-js"                      %%% "scalajs-dom" % scalaJSDom,
        "org.typelevel"                     %%% "cats-core"   % cats,
        "io.github.cquiroz.react"           %%% "common"      % reactCommon,
        "com.lihaoyi"                       %%% "utest"       % utest        % Test
      ),
      Test / webpackConfigFile        := Some(
        baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion           := "2.13.8",
  organization           := "io.github.cquiroz.react",
  sonatypeProfileName    := "io.github.cquiroz",
  description            := "scala.js facade for react-grid-layout ",
  Test / publishArtifact := false,
  scalacOptions ~= (_.filterNot(
    Set(
      // By necessity facades will have unused params
      "-Wdead-code"
    )
  ))
)
