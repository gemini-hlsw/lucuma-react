import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / tlBaseVersion       := "0.45"
ThisBuild / tlCiReleaseBranches := Seq("main")
ThisBuild / githubWorkflowTargetBranches += "!dependabot/**"

ThisBuild / githubWorkflowBuildPreamble ++= Seq(
  WorkflowStep.Use(
    UseRef.Public("actions", "setup-node", "v3"),
    name = Some("Setup Node"),
    params = Map("node-version" -> "18", "cache" -> "npm")
  ),
  WorkflowStep.Run(List("npm ci"))
)

ThisBuild / mergifyPrRules +=
  MergifyPrRule(
    "merge dependabot PRs",
    MergifyCondition.Custom("author=dependabot[bot]") :: mergifySuccessConditions.value.toList,
    List(MergifyAction.Merge())
  )

val lucumaTypedV     = "0.5.1"
val catsV            = "2.10.0"
val disciplineMunitV = "2.0.0-M3"
val kittensV         = "3.0.0"
val munitV           = "1.0.0-M10"
val scalaJsReactV    = "2.1.1"
val utestV           = "0.8.1"
val http4sV          = "0.23.23"

ThisBuild / crossScalaVersions := Seq("3.3.1")

lazy val facadeSettings = Seq(
  libraryDependencies ++= Seq(
    "com.github.japgolly.scalajs-react" %%% "core"      % scalaJsReactV,
    "com.github.japgolly.scalajs-react" %%% "extra"     % scalaJsReactV,
    "com.github.japgolly.scalajs-react" %%% "test"      % scalaJsReactV % Test,
    "org.typelevel"                     %%% "cats-core" % catsV,
    "org.typelevel"                     %%% "kittens"   % kittensV,
    "com.lihaoyi"                       %%% "utest"     % utestV        % Test,
    "org.scalameta"                     %%% "munit"     % munitV        % Test
  ),
  jsEnv := new lucuma.LucumaJSDOMNodeJSEnv(),
  scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  testFrameworks += new TestFramework("utest.runner.Framework")
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
    tanstackTable,
    tanstackTableDemo,
    highcharts,
    highchartsDemo,
    datepicker,
    datepickerDemo,
    beautifulDnd,
    beautifulDndDemo,
    resizeDetector,
    fontAwesome,
    fontAwesomeDemo,
    hotkeys,
    hotkeysDemo,
    resizable,
    resizableDemo,
    primeReact,
    primeReactDemo,
    circularProgressbar,
    markdown
  )

lazy val rootCore          = project.aggregate(common, test).enablePlugins(NoPublishPlugin)
lazy val rootGridLayout    =
  project.aggregate(resizeDetector, gridLayout, gridLayoutDemo).enablePlugins(NoPublishPlugin)
lazy val rootDraggable     =
  project.aggregate(draggable).enablePlugins(NoPublishPlugin)
lazy val rootFloatingui    =
  project.aggregate(floatingui).enablePlugins(NoPublishPlugin)
lazy val rootClipboard     =
  project.aggregate(clipboard).enablePlugins(NoPublishPlugin)
lazy val rootTanstackTable =
  project.aggregate(tanstackTable, tanstackTableDemo).enablePlugins(NoPublishPlugin)
lazy val rootHighcharts    =
  project.aggregate(highcharts, highchartsDemo).enablePlugins(NoPublishPlugin)
lazy val rootDatepicker    =
  project.aggregate(datepicker, datepickerDemo).enablePlugins(NoPublishPlugin)
lazy val rootBeautifulDnd  =
  project.aggregate(beautifulDnd, beautifulDndDemo).enablePlugins(NoPublishPlugin)
lazy val rootFontAwesome   =
  project.aggregate(fontAwesome, fontAwesomeDemo).enablePlugins(NoPublishPlugin)
lazy val rootHotkeys       =
  project.aggregate(hotkeys, hotkeysDemo).enablePlugins(NoPublishPlugin)
lazy val rootResizable     =
  project.aggregate(resizable, resizableDemo).enablePlugins(NoPublishPlugin)
lazy val rootPrimeReact    =
  project.aggregate(primeReact, primeReactDemo).enablePlugins(NoPublishPlugin)

val projects = List(
  rootCore,
  rootGridLayout,
  rootDraggable,
  rootFloatingui,
  rootClipboard,
  rootTanstackTable,
  rootHighcharts,
  rootDatepicker,
  rootBeautifulDnd,
  rootFontAwesome,
  rootHotkeys,
  rootResizable,
  rootPrimeReact,
  circularProgressbar,
  markdown
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
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name  := "lucuma-react-test",
    jsEnv := new lucuma.LucumaJSDOMNodeJSEnv(),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    libraryDependencies ++= Seq(
      "org.scalameta"                     %%% "munit"            % munitV,
      "org.typelevel"                     %%% "discipline-munit" % disciplineMunitV % Test,
      "com.github.japgolly.scalajs-react" %%% "test"             % scalaJsReactV    % Test
    )
  )
  .dependsOn(common)

lazy val gridLayout = project
  .in(file("grid-layout"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-grid-layout",
    facadeSettings
  )

lazy val gridLayoutDemo = project
  .in(file("grid-layout-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(gridLayout, resizeDetector)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "pprint" % "0.8.1",
    demoSettings
  )

lazy val draggable = project
  .in(file("draggable"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-draggable",
    facadeSettings
  )

lazy val floatingui = project
  .in(file("floatingui"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common, test % Test)
  .settings(
    name := "lucuma-react-floatingui",
    facadeSettings
  )

lazy val clipboard = project
  .in(file("clipboard"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common, test % Test)
  .settings(
    name := "lucuma-react-clipboard",
    facadeSettings
  )

lazy val tanstackTable = project
  .in(file("tanstack-table"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-tanstack-table",
    Compile / scalacOptions += "-language:implicitConversions",
    libraryDependencies ++= Seq(
      "edu.gemini" %%% "lucuma-typed-tanstack-react-table"   % lucumaTypedV,
      "edu.gemini" %%% "lucuma-typed-tanstack-react-virtual" % lucumaTypedV
    )
  )

lazy val tanstackTableDemo = project
  .in(file("tanstack-table-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(tanstackTable)
  .settings(
    demoSettings
  )
  .settings(
    Compile / scalacOptions += "-language:implicitConversions"
  )

lazy val highcharts = project
  .in(file("highcharts"))
  .enablePlugins(ScalaJSPlugin, ScalaJSImportMapPlugin)
  .dependsOn(common, resizeDetector)
  .settings(
    name                    := "lucuma-react-highcharts",
    Compile / scalacOptions += "-language:implicitConversions",
    libraryDependencies ++= Seq(
      "edu.gemini" %%% "lucuma-typed-highcharts" % lucumaTypedV
    ),
    facadeSettings,
    Test / scalaJSImportMap := {
      case "highcharts/es-modules/masters/highcharts.src.js"            => "highcharts"
      case "highcharts/es-modules/masters/modules/accessibility.src.js" =>
        "highcharts/modules/accessibility"
      case other                                                        => other
    }
  )

lazy val highchartsDemo = project
  .in(file("highcharts-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(highcharts)
  .settings(
    tlFatalWarnings := false,
    demoSettings
  )

lazy val datepicker = project
  .in(file("datepicker"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-datepicker",
    Compile / scalacOptions += "-language:implicitConversions",
    libraryDependencies ++= Seq(
      "edu.gemini" %%% "lucuma-typed-react-datepicker" % lucumaTypedV
    ),
    facadeSettings
  )

lazy val datepickerDemo = project
  .in(file("datepicker-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(datepicker)
  .settings(
    demoSettings,
    Compile / scalacOptions += "-language:implicitConversions",
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.5.0"
    )
  )

lazy val beautifulDnd = project
  .in(file("beautiful-dnd"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-beautiful-dnd",
    facadeSettings
  )

lazy val beautifulDndDemo = project
  .in(file("beautiful-dnd-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(beautifulDnd)
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
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings
  )

lazy val fontAwesomeDemo = project
  .in(file("font-awesome-demo"))
  .enablePlugins(ScalaJSPlugin, NoPublishPlugin)
  .dependsOn(fontAwesome)
  .settings(
    Compile / scalacOptions += "-language:implicitConversions",
    demoSettings
  )

lazy val hotkeys = project
  .in(file("hotkeys-hooks"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name := "lucuma-react-hotkeys-hooks",
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings
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
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common, draggable)
  .settings(
    name := "lucuma-react-resizable",
    Compile / scalacOptions += "-language:implicitConversions",
    facadeSettings
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
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common, fontAwesome)
  .settings(
    name := "lucuma-react-prime-react",
    Compile / scalacOptions += "-language:implicitConversions",
    libraryDependencies ++= Seq(
      "edu.gemini" %%% "lucuma-typed-primereact" % lucumaTypedV
    )
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
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common, test % Test)
  .settings(
    name := "lucuma-react-circular-progressbar",
    facadeSettings
  )

lazy val markdown = project
  .in(file("markdown"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(common)
  .settings(
    name      := "lucuma-react-markdown",
    facadeSettings,
    Keys.test := {},
    libraryDependencies ++= Seq(
      "org.http4s" %%% "http4s-core" % http4sV
    )
  )
