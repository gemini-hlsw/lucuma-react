import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / tlBaseVersion       := "1.0"
ThisBuild / tlCiReleaseBranches := Seq("main")

val scalaJsReactV    = "2.0.1"
val catsV            = "2.7.0"
val munitV           = "0.7.29"
val disciplineMunitV = "1.0.9"
val utestV           = "0.7.11"

ThisBuild / crossScalaVersions := Seq("3.1.1")

lazy val yarnSettings = Seq(
  useYarn := true,
  yarnExtraArgs ++= {
    if (githubIsWorkflowBuild.value)
      List("--frozen-lockfile")
    else Nil
  }
)

lazy val root = project
  .in(file("."))
  .enablePlugins(NoPublishPlugin)
  .aggregate(common, cats, test, gridLayout, gridLayoutDemo)

lazy val common = project
  .in(file("common"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "lucuma-react-common",
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core" % scalaJsReactV
    )
  )

lazy val cats = project
  .in(file("cats"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "lucuma-react-cats",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core"        % catsV,
      "org.typelevel" %%% "cats-laws"        % catsV            % Test,
      "org.scalameta" %%% "munit-scalacheck" % munitV           % Test,
      "org.typelevel" %%% "discipline-munit" % disciplineMunitV % Test
    )
  )
  .dependsOn(common)

lazy val test = project
  .in(file("test"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(
    yarnSettings,
    Test / requireJsDomEnv   := true,
    libraryDependencies ++= Seq(
      "org.scalameta"                     %%% "munit"            % munitV,
      "org.typelevel"                     %%% "discipline-munit" % disciplineMunitV % Test,
      "com.github.japgolly.scalajs-react" %%% "test"             % scalaJsReactV    % Test
    ),
    Test / webpackConfigFile := Some(
      baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
    )
  )
  .dependsOn(cats, common)

lazy val gridLayout = project
  .in(file("grid-layout"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    Test / requireJsDomEnv   := true,
    yarnSettings,
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core"      % scalaJsReactV,
      "com.github.japgolly.scalajs-react" %%% "extra"     % scalaJsReactV,
      "com.github.japgolly.scalajs-react" %%% "test"      % scalaJsReactV % Test,
      "org.typelevel"                     %%% "cats-core" % catsV,
      "com.lihaoyi"                       %%% "utest"     % utestV        % Test
    ),
    Test / webpackConfigFile := Some(
      baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

lazy val gridLayoutDemo = project
  .in(file("grid-layout-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(gridLayout)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.7.1",
    Compile / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    Compile / fastLinkJS / scalaJSLinkerConfig ~= (_.withModuleSplitStyle(
      ModuleSplitStyle.SmallestModules
    )),
    Keys.test                             := {}
  )
