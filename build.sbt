name := "scalajs-react-hotkeys"

scalaVersion in ThisBuild := "2.13.3"

Global / onChangedBuildSource := ReloadOnSourceChanges

val reactHotkeys = "2.0.0"
val scalaJsReact = "1.7.5"
val reactJS      = "16.13.1"

parallelExecution in (ThisBuild, Test) := false

addCommandAlias(
  "restartWDS",
  "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer"
)

inThisBuild(
  List(
    homepage := Some(
      url("https://github.com/rpiaggio/scalajs-react-hotkeys")
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
        url("https://github.com/rpiaggio/scalajs-react-hotkeys"),
        "scm:git:git@github.com:rpiaggio/scalajs-react-hotkeys.git"
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
      name := "scalajs-react-hotkeys",
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
    .settings(commonSettings: _*)
    .settings(
      version in webpack := "4.32.0",
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
        "postcss-loader"                     -> "3.0.0",
        "autoprefixer"                       -> "9.4.4",
        "url-loader"                         -> "1.1.1",
        "file-loader"                        -> "3.0.1",
        "css-loader"                         -> "2.1.0",
        "style-loader"                       -> "0.23.1",
        "less"                               -> "3.9.0",
        "less-loader"                        -> "4.1.0",
        "webpack-merge"                      -> "4.2.1",
        "mini-css-extract-plugin"            -> "0.5.0",
        "webpack-dev-server-status-bar"      -> "1.1.0",
        "cssnano"                            -> "4.1.8",
        "uglifyjs-webpack-plugin"            -> "2.1.1",
        "html-webpack-plugin"                -> "3.2.0",
        "optimize-css-assets-webpack-plugin" -> "5.0.1",
        "favicons-webpack-plugin"            -> "0.0.9",
        "why-did-you-update"                 -> "1.0.6"
      ),
      npmDependencies in Compile ++= Seq(
        "react"         -> reactJS,
        "react-dom"     -> reactJS,
        "react-hotkeys" -> reactHotkeys
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
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name := "facade",
      moduleName := "scalajs-react-hotkeys",
      // Requires the DOM for tests
      requireJsDomEnv in Test := true,
      // Use yarn as it is faster than npm
      useYarn := true,
      version in webpack := "4.32.0",
      version in installJsdom := "15.2.1",
      scalaJSUseMainModuleInitializer := false,
      // Compile tests to JS using fast-optimisation
      scalaJSStage in Test := FastOptStage,
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"   % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test"   % scalaJsReact % "test",
        "io.github.cquiroz.react"           %%% "common" % "0.9.6",
        "com.lihaoyi"                       %%% "utest"  % "0.7.5"      % Test
      ),
      npmDependencies in Compile ++= Seq(
        "react"         -> reactJS,
        "react-dom"     -> reactJS,
        "react-hotkeys" -> reactHotkeys
      ),
      webpackConfigFile in Test := Some(
        baseDirectory.value / "test.webpack.config.js"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion := scalaVersion.value,
  organization := "com.rpiaggio",
  sonatypeProfileName := "com.rpiaggio",
  description := "react-hotkeys in scalajs-react",
  homepage := Some(url("https://github.com/rpiaggio/scalajs-react-hotkeys")),
  licenses := Seq(
    "BSD 3-Clause License" -> url(
      "https://opensource.org/licenses/BSD-3-Clause"
    )
  ),
  publishArtifact in Test := false,
  publishMavenStyle := true,
  scalacOptions := Seq(
    "-deprecation",                  // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8",                         // Specify character encoding used by source files.
    "-explaintypes",                 // Explain type errors in more detail.
    "-feature",                      // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials",        // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds",         // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit",                   // Wrap field accessors to throw an exception on uninitialized access.
    "-Xfatal-warnings",              // Fail the compilation if there are any warnings.
    // "-Xfuture",                          // Turn on future language features.
    "-Xlint:adapted-args",           // Warn if an argument list is modified to match the receiver.
    // "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
    "-Xlint:constant",               // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select",     // Selecting member of DelayedInit.
    "-Xlint:doc-detached",           // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible",           // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any",              // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",   // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-override",       // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Xlint:nullary-unit",           // Warn when nullary methods return Unit.
    "-Xlint:option-implicit",        // Option.apply used implicit view.
    "-Xlint:package-object-classes", // Class or object defined in package object.
    "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow",         // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align",            // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow",  // A local type parameter shadows a type already in scope.
    // "-Xlint:unsound-match",              // Pattern match may not be typesafe.
    // "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
    // "-Ypartial-unification",             // Enable partial unification in type constructor inference
    "-Ywarn-extra-implicit",         // Warn when more than one implicit parameter section is defined.
    // "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
    // "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
    // "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
    // "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
    "-Ywarn-numeric-widen",          // Warn when numerics are widened.
    "-Ywarn-unused:implicits",       // Warn if an implicit parameter is unused.
    // "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
    // "-Ywarn-unused:locals",              // Warn if a local definition is unused.
    // "-Ywarn-unused:params",              // Warn if a value parameter is unused.
    // "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates",        // Warn if a private member is unused.
    "-Ywarn-value-discard",          // Warn when non-Unit expression results are unused.
    //      "-Ywarn-dead-code",
    "-Yrangepos"
  )
)
