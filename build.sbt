name := "scalajs-react-datepicker"

Global / onChangedBuildSource := ReloadOnSourceChanges

val reactDatePicker = "3.1.3"
val reactDatePickerTypes = "3.1.0"
val scalaJsReact = "1.7.3"
val reactJS = "16.13.1"

parallelExecution in (ThisBuild, Test) := false

addCommandAlias(
  "restartWDS",
  "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

inThisBuild(
  List(
    scalaVersion := "2.13.3",
    organization := "com.rpiaggio",
    sonatypeProfileName := "com.rpiaggio",
    homepage := Some(
      url("https://github.com/rpiaggio/scalajs-react-datepicker")
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
        url("https://github.com/rpiaggio/scalajs-react-datepicker"),
        "scm:git:git@github.com:rpiaggio/scalajs-react-datepicker.git"
      )
    )
  )
)

val root =
  project
    .in(file("."))
    .aggregate(facade, demo)
    .settings(
      name := "scalajs-react-datepicker",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )

lazy val demo =
  project
    .in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      version in webpack := "4.36.0",
      version in startWebpackDevServer := "3.3.1",
      webpackConfigFile in fastOptJS := Some(
        baseDirectory.value / "webpack" / "dev.webpack.config.js"
      ),
      webpackConfigFile in fullOptJS := Some(
        baseDirectory.value / "webpack" / "prod.webpack.config.js"
      ),
      webpackMonitoredDirectories += (resourceDirectory in Compile).value,
      webpackResources := (baseDirectory.value / "webpack") * "*.js",
      includeFilter in webpackMonitoredFiles := "*",
      webpackExtraArgs := Seq("--progress"),
      useYarn := true,
      webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly(),
      webpackBundlingMode in fullOptJS := BundlingMode.Application,
      test := {},
      scalaJSLinkerConfig in (Compile, fastOptJS) ~= { _.withSourceMap(false) },
      scalaJSLinkerConfig in (Compile, fullOptJS) ~= { _.withSourceMap(false) },
      // NPM libs for development, mostly to let webpack do its magic
      npmDevDependencies in Compile ++= Seq(
        "postcss-loader" -> "3.0.0",
        "autoprefixer" -> "9.4.4",
        "url-loader" -> "1.1.1",
        "file-loader" -> "3.0.1",
        "css-loader" -> "2.1.0",
        "style-loader" -> "0.23.1",
        "less" -> "3.9.0",
        "less-loader" -> "4.1.0",
        "sass" -> "1.26.10",
        "sass-loader" -> "9.0.2",
        "webpack-merge" -> "4.2.1",
        "mini-css-extract-plugin" -> "0.5.0",
        "webpack-dev-server-status-bar" -> "1.1.0",
        "cssnano" -> "4.1.8",
        "uglifyjs-webpack-plugin" -> "2.1.1",
        "html-webpack-plugin" -> "3.2.0",
        "optimize-css-assets-webpack-plugin" -> "5.0.1",
        "favicons-webpack-plugin" -> "0.0.9"
      ),
      npmDependencies in Compile ++= Seq(
        "react" -> reactJS,
        "react-dom" -> reactJS,
        "react-datepicker" -> reactDatePicker
      ),
      libraryDependencies ++= Seq(
        "io.github.cquiroz" %%% "scala-java-time" % "2.0.0"
      ),
      // don't publish the demo
      publish := {},
      publishLocal := {},
      publishArtifact := false,
      Keys.`package` := file("")
    )
    .dependsOn(facade)

lazy val facade =
  project
    .in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin, ScalablyTypedConverterGenSourcePlugin)
    .settings(
      name := "facade",
      moduleName := "scalajs-react-datepicker",
      // Requires the DOM for tests
      requireJsDomEnv in Test := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      yarnExtraArgs := {
        if (insideCI.value) List("--frozen-lockfile") else List.empty
      },
      version in webpack := "4.36.0",
      version in installJsdom := "15.2.1",
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      scalaJSStage in Test := FastOptStage,
      npmDependencies in Compile ++= Seq(
        "react" -> reactJS,
        "react-dom" -> reactJS,
        "react-datepicker" -> reactDatePicker,
        "@types/react-datepicker" -> reactDatePickerTypes
      ),
      stUseScalaJsDom := true,
      stOutputPackage := "gpp",
      stFlavour := Flavour.Japgolly,
      Compile / stMinimize := Selection.AllExcept("@types/react-datepicker"),
      // stEnableScalaJsDefined := Selection.All,
      scalacOptions ~= (_.filterNot(
        Set(
          // By necessity facades will have unused params
          "-Wdead-code",
          "-Wunused:params",
          "-Wunused:explicits",
          "-Wunused:imports"
        )
      )),
      webpackConfigFile in Test := Some(
        baseDirectory.value / "test.webpack.config.js"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )
