import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / tlBaseVersion       := "1.0"
ThisBuild / tlCiReleaseBranches := Seq("main")

val scalaJsReactV    = "2.0.1"
val catsV            = "2.7.0"
val munitV           = "0.7.29"
val disciplineMunitV = "1.0.9"
val utestV           = "0.7.11"

ThisBuild / crossScalaVersions := Seq("3.1.2")

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
    draggable,
    clipboard,
    svgdotjs,
    virtuoso,
    virtuosoDemo,
    table,
    tableDemo,
    highcharts,
    highchartsDemo,
    datepicker,
    datepickerDemo,
    beautifulDnd,
    beautifulDndDemo,
    tree,
    treeDemo
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
    name                     := "lucuma-react-test",
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
    name := "lucuma-react-grid-layout",
    facadeSettings,
    yarnSettings
  )

lazy val gridLayoutDemo = project
  .in(file("grid-layout-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(gridLayout)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.7.3",
    demoSettings
  )

lazy val draggable = project
  .in(file("draggable"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-draggable",
    facadeSettings,
    yarnSettings
  )

lazy val clipboard = project
  .in(file("clipboard"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common, test % Test)
  .settings(
    name := "lucuma-react-clipboard",
    facadeSettings,
    yarnSettings
  )

lazy val svgdotjs = project
  .in(file("svgdotjs"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
  .settings(
    name                    := "lucuma-svgdotjs",
    stOutputPackage         := "lucuma.svgdotjs",
    stSourceGenMode         := SourceGenMode.ResourceGenerator,
    stUseScalaJsDom         := true,
    stMinimize              := Selection.AllExcept("@svgdotjs/svg.js"),
    scalaJSLinkerConfig ~= (_.withSourceMap(false)),
    tlFatalWarnings         := false,
    Compile / doc / sources := Seq(),
    yarnSettings
  )

lazy val virtuoso = project
  .in(file("virtuoso"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-virtuoso",
    facadeSettings,
    yarnSettings
  )

lazy val virtuosoDemo = project
  .in(file("virtuoso-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(virtuoso)
  .settings(
    demoSettings
  )

lazy val table = project
  .in(file("table"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
  .dependsOn(common, virtuoso)
  .settings(
    name                     := "lucuma-react-table",
    stOutputPackage          := "reactST",
    stUseScalaJsDom          := true,
    stFlavour                := Flavour.ScalajsReact,
    stReactEnableTreeShaking := Selection.All,
    stMinimize               := Selection.AllExcept("react-table"),
    Compile / doc / sources  := Seq(),
    Compile / scalacOptions += "-language:implicitConversions",
    yarnSettings
  )

lazy val tableDemo = project
  .in(file("table-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(table)
  .settings(
    demoSettings
  )

lazy val highcharts = project
  .in(file("highcharts"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
  .dependsOn(common)
  .settings(
    name                    := "lucuma-react-highcharts",
    stIgnore ++= List("react", "react-dom"),
    stUseScalaJsDom         := true,
    stOutputPackage         := "gpp",
    stMinimize              := Selection.AllExcept("highcharts"),
    Compile / doc / sources := Seq(),
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings,
    yarnSettings
  )

lazy val highchartsDemo = project
  .in(file("highcharts-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(highcharts)
  .settings(
    demoSettings
  )

lazy val datepicker = project
  .in(file("datepicker"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
  .dependsOn(common)
  .settings(
    name                    := "lucuma-react-datepicker",
    stIgnore ++= List("react-dom"),
    stUseScalaJsDom         := true,
    stOutputPackage         := "lucuma",
    stFlavour               := Flavour.ScalajsReact,
    stMinimize              := Selection.AllExcept("@types/react-datepicker"),
    Compile / doc / sources := Seq(),
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings,
    yarnSettings
  )

lazy val datepickerDemo = project
  .in(file("datepicker-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(datepicker)
  .settings(
    demoSettings,
    Compile / scalacOptions += "-language:implicitConversions",
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.3.0"
    )
  )

lazy val beautifulDnd = project
  .in(file("beautiful-dnd"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-beautiful-dnd",
    facadeSettings,
    yarnSettings
  )

lazy val beautifulDndDemo = project
  .in(file("beautiful-dnd-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(beautifulDnd)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )

lazy val tree = project
  .in(file("tree"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common, beautifulDnd)
  .settings(
    name := "lucuma-react-tree",
    facadeSettings,
    yarnSettings
  )

lazy val treeDemo = project
  .in(file("tree-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(tree)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )
