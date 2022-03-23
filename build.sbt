import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / tlBaseVersion       := "1.0"
ThisBuild / tlCiReleaseBranches := Seq("main")

val scalaJsReactV    = "2.0.1"
val catsV            = "2.7.0"
val munitV           = "0.7.29"
val disciplineMunitV = "1.0.9"
val utestV           = "0.7.11"

ThisBuild / crossScalaVersions := Seq("3.1.2-RC2")

lazy val facadeSettings = Seq(
  libraryDependencies ++= Seq(
    "com.github.japgolly.scalajs-react" %%% "core"      % scalaJsReactV,
    "com.github.japgolly.scalajs-react" %%% "extra"     % scalaJsReactV,
    "com.github.japgolly.scalajs-react" %%% "test"      % scalaJsReactV % Test,
    "org.typelevel"                     %%% "cats-core" % catsV,
    "com.lihaoyi"                       %%% "utest"     % utestV        % Test,
    "org.scalameta"                     %%% "munit"     % munitV        % Test
  ),
  Test / webpackConfigFile := Some(
    baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
  ),
  Test / requireJsDomEnv   := true,
  testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val yarnSettings = Seq(
  useYarn := true,
  yarnExtraArgs ++= {
    if (githubIsWorkflowBuild.value)
      List("--frozen-lockfile")
    else Nil
  }
)

lazy val viteConfigGenerate = taskKey[Unit]("Generate vite config")
lazy val demoSettings       = Seq(
  Compile / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
  Compile / fastLinkJS / scalaJSLinkerConfig ~= (_.withModuleSplitStyle(
    ModuleSplitStyle.SmallestModules
  )),
  Keys.test          := {},
  viteConfigGenerate := {
    val prodTarget =
      (Compile / fullLinkJS / scalaJSLinkerOutputDirectory).value
        .relativeTo(baseDirectory.value)
        .get
    val devTarget  =
      (Compile / fastLinkJS / scalaJSLinkerOutputDirectory).value
        .relativeTo(baseDirectory.value)
        .get
    IO.write(
      baseDirectory.value / "vite.config.js",
      s"""|import react from "@vitejs/plugin-react";
          |import path from "path";
          |
          |// https://vitejs.dev/config/
          |export default ({ command, mode }) => {
          |  const sjs =
          |    mode == "production"
          |      ? path.resolve(__dirname, "$prodTarget/")
          |      : path.resolve(__dirname, "$devTarget/");
          |  return {
          |    root: "${baseDirectory.value.getName}/src/main/webapp",
          |    resolve: {
          |      alias: [
          |        {
          |          find: "@sjs",
          |          replacement: sjs,
          |        },
          |      ],
          |    },
          |    server: {
          |      watch: {
          |        ignored: [
          |          function ignoreThisPath(_path) {
          |            const sjsIgnored =
          |              _path.includes("/target/stream") ||
          |              _path.includes("/zinc/") ||
          |              _path.includes("/classes");
          |            return sjsIgnored;
          |          },
          |        ],
          |      },
          |    },
          |    build: {
          |      minify: 'terser',
          |      // minify: false,
          |      terserOptions: {
          |        // sourceMap: false,
          |        toplevel: true
          |      },
          |      outDir: path.resolve(__dirname, "../docs"),
          |    },
          |    plugins: [react()],
          |  };
          |};
          |""".stripMargin
    )
  }
)

lazy val root = project
  .in(file("."))
  .enablePlugins(NoPublishPlugin)
  .aggregate(
    common,
    cats,
    test,
    gridLayout,
    gridLayoutDemo,
    draggable
    // draggableDemo // TODO
  )

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
    facadeSettings,
    yarnSettings
  )

lazy val gridLayoutDemo = project
  .in(file("grid-layout-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(gridLayout)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.7.1",
    demoSettings
  )

lazy val draggable = project
  .in(file("draggable"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    facadeSettings,
    yarnSettings
  )

lazy val draggableDemo = project
  .in(file("draggable-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(draggable)
  .settings(
    demoSettings
  )
