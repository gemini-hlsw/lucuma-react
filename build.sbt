import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / tlBaseVersion       := "0.3"
ThisBuild / tlCiReleaseBranches := Seq("main")
ThisBuild / githubWorkflowTargetBranches += "!dependabot/**"

ThisBuild / mergifyPrRules +=
  MergifyPrRule(
    "merge dependabot PRs",
    MergifyCondition.Custom("author=dependabot[bot]") :: mergifySuccessConditions.value.toList,
    List(MergifyAction.Merge())
  )

val scalaJsReactV    = "2.1.1"
val catsV            = "2.8.0"
val munitV           = "1.0.0-M6"
val disciplineMunitV = "2.0.0-M3"
val utestV           = "0.8.1"
val jsdomV           = "20.0.0"

ThisBuild / crossScalaVersions := Seq("3.2.0")

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
  installJsdom / version   := jsdomV,
  testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val yarnSettings = Seq(
  useYarn := true,
  yarnExtraArgs ++= {
    if (githubIsWorkflowBuild.value && !githubWorkflowName.?.value.contains("Update Lockfiles"))
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
          |  const webapp = path.resolve(__dirname, "src/main/webapp/");
          |  const themeConfig = path.resolve(webapp, "theme/theme.config");
          |  const themeSite = path.resolve(webapp, "theme/site");
          |  return {
          |    root: "${baseDirectory.value.getName}/src/main/webapp",
          |    resolve: {
          |      alias: [
          |        {
          |          find: "@sjs",
          |          replacement: sjs,
          |        },
          |        {
          |          find: "../../theme.config",
          |          replacement: themeConfig,
          |        },
          |        {
          |          find: "theme/site",
          |          replacement: themeSite,
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
    test,
    gridLayout,
    gridLayoutDemo,
    draggable,
    floatingui,
    clipboard,
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
    treeDemo,
    semanticUI,
    semanticUIDemo,
    resizeDetector,
    fontAwesome,
    hotkeys,
    hotkeysDemo,
    resizable,
    resizableDemo,
    primeReact,
    primeReactDemo,
    circularProgressbar,
    moon
  )

lazy val rootCore         = project.aggregate(common, test).enablePlugins(NoPublishPlugin)
lazy val rootGridLayout   =
  project.aggregate(resizeDetector, gridLayout, gridLayoutDemo).enablePlugins(NoPublishPlugin)
lazy val rootDraggable    =
  project.aggregate(draggable).enablePlugins(NoPublishPlugin)
lazy val rootFloatingui   =
  project.aggregate(floatingui).enablePlugins(NoPublishPlugin)
lazy val rootClipboard    =
  project.aggregate(clipboard).enablePlugins(NoPublishPlugin)
lazy val rootVirtuoso     =
  project.aggregate(virtuoso, virtuosoDemo).enablePlugins(NoPublishPlugin)
lazy val rootTable        =
  project.aggregate(table, tableDemo).enablePlugins(NoPublishPlugin)
lazy val rootHighcharts   =
  project.aggregate(highcharts, highchartsDemo).enablePlugins(NoPublishPlugin)
lazy val rootDatepicker   =
  project.aggregate(datepicker, datepickerDemo).enablePlugins(NoPublishPlugin)
lazy val rootBeautifulDnd =
  project.aggregate(beautifulDnd, beautifulDndDemo).enablePlugins(NoPublishPlugin)
lazy val rootTree         =
  project.aggregate(tree, treeDemo).enablePlugins(NoPublishPlugin)
lazy val rootSemanticUI   =
  project.aggregate(semanticUI, semanticUIDemo).enablePlugins(NoPublishPlugin)
lazy val rootHotkeys      =
  project.aggregate(hotkeys, hotkeysDemo).enablePlugins(NoPublishPlugin)
lazy val rootResizable    =
  project.aggregate(resizable, resizableDemo).enablePlugins(NoPublishPlugin)
lazy val rootPrimeReact   =
  project.aggregate(primeReact, primeReactDemo).enablePlugins(NoPublishPlugin)

val projects = List(
  rootCore,
  rootGridLayout,
  rootDraggable,
  rootFloatingui,
  rootClipboard,
  rootVirtuoso,
  rootTable,
  rootHighcharts,
  rootDatepicker,
  rootBeautifulDnd,
  rootTree,
  rootSemanticUI,
  fontAwesome,
  rootHotkeys,
  rootResizable,
  rootPrimeReact,
  circularProgressbar,
  moon
).map(_.id)
ThisBuild / githubWorkflowBuildMatrixAdditions += "project" -> projects
ThisBuild / githubWorkflowBuildSbtStepPreamble += s"project $${{ matrix.project }}"

lazy val common = project
  .in(file("common"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "lucuma-react-common",
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core"             % scalaJsReactV,
      "org.typelevel"                     %%% "cats-core"        % catsV,
      "org.typelevel"                     %%% "cats-laws"        % catsV            % Test,
      "org.scalameta"                     %%% "munit-scalacheck" % munitV           % Test,
      "org.typelevel"                     %%% "discipline-munit" % disciplineMunitV % Test
    )
  )

lazy val test = project
  .in(file("test"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(
    name                     := "lucuma-react-test",
    yarnSettings,
    Test / requireJsDomEnv   := true,
    installJsdom / version   := jsdomV,
    libraryDependencies ++= Seq(
      "org.scalameta"                     %%% "munit"            % munitV,
      "org.typelevel"                     %%% "discipline-munit" % disciplineMunitV % Test,
      "com.github.japgolly.scalajs-react" %%% "test"             % scalaJsReactV    % Test
    ),
    Test / webpackConfigFile := Some(
      baseDirectory.value / "src" / "webpack" / "test.webpack.config.js"
    )
  )
  .dependsOn(common)

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
  .dependsOn(gridLayout, resizeDetector)
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

lazy val floatingui = project
  .in(file("floatingui"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-floatingui",
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
  .dependsOn(common, resizeDetector)
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
      "io.github.cquiroz" %%% "scala-java-time" % "2.4.0"
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

lazy val semanticUI = project
  .in(file("semantic-ui"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-semantic-ui",
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings,
    yarnSettings
  )

lazy val semanticUIDemo = project
  .in(file("semantic-ui-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(semanticUI)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )

lazy val resizeDetector = project
  .in(file("resize-detector"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-resize-detector",
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings
  )

lazy val fontAwesome = project
  .in(file("font-awesome"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-font-awesome",
    facadeSettings
  )

lazy val hotkeys = project
  .in(file("hotkeys"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-hotkeys",
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings,
    yarnSettings
  )

lazy val hotkeysDemo = project
  .in(file("hotkeys-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(hotkeys)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )

lazy val resizable = project
  .in(file("resizable"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common, draggable)
  .settings(
    name := "lucuma-react-resizable",
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings,
    yarnSettings
  )

lazy val resizableDemo = project
  .in(file("resizable-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(resizable)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )

lazy val primeReact = project
  .in(file("prime-react"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
  .settings(
    name                                                      := "lucuma-react-prime-react",
    stOutputPackage                                           := "reactST",
    stUseScalaJsDom                                           := true,
    stFlavour                                                 := Flavour.ScalajsReact,
    stReactEnableTreeShaking                                  := Selection.All,
    stMinimize                                                := Selection.AllExcept("primereact"),
    Compile / doc / sources                                   := Seq(),
    Compile / scalacOptions += "-language:implicitConversions",
    yarnSettings,
    ScalablyTypedConverterGenSourcePlugin.autoImport.stImport := {
      val rtn     = ScalablyTypedConverterGenSourcePlugin.autoImport.stImport.value
      val sources = (Compile / sourceManaged).value / "scalablytyped" ** "*.scala"
      sources.get().foreach { f =>
        val content     = IO.read(f)
        // use the ESM-style sources in imports
        val transformed = content.replaceAll(
          """@JSImport\("primereact\/((.+?)(?<!\.esm))",""",
          """@JSImport("primereact/$1.esm","""
        )
        if (transformed != content)
          IO.write(f, transformed)
      }
      rtn
    }
  )

lazy val primeReactDemo = project
  .in(file("prime-react-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(primeReact)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )

lazy val circularProgressbar = project
  .in(file("circular-progressbar"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common, test % Test)
  .settings(
    name                := "lucuma-react-circular-progressbar",
    facadeSettings,
    yarnSettings,
    tlVersionIntroduced := Map("3" -> "0.1.1")
  )

lazy val moon = project
  .in(file("moon"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(common, test % Test)
  .settings(
    name                := "lucuma-react-moon",
    facadeSettings,
    yarnSettings,
    tlVersionIntroduced := Map("3" -> "0.1.1")
  )
