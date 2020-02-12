name := "scalajs-react-beautiful-dnd"

version in ThisBuild := "0.0.1"

scalaVersion in ThisBuild := "2.13.1"

Global / onChangedBuildSource := IgnoreSourceChanges

val reactJS = "16.8.6"
val resactBeautiulDnD = "12.2.0"
val scalaJsReact = "1.6.0"

val jQuery                  = "3.4.1"
val semanticUI              = "2.4.2"

parallelExecution in (ThisBuild, Test) := false

addCommandAlias("restartWDS", "; demo/fastOptJS::stopWebpackDevServer; demo/fastOptJS::startWebpackDevServer")

// resolvers in Global += Resolver.sonatypeRepo("staging")

// sbt-release-early
inThisBuild(List(
  homepage                := Some(url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd")),
  licenses                := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
  developers := List(
    Developer("rpiaggio", "Raúl Piaggio", "rpiaggio@gmail.com", url("https://github.com/rpiaggio")),
    Developer("cquiroz", "Carlos Quiroz", "carlos.m.quiroz@gmail.com", url("https://github.com/cquiroz"))
  ),
  scmInfo := Some(ScmInfo(url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd"), "scm:git:git@github.com:rpiaggio/scalajs-react-beautiful-dnd.git")),
  // These are the sbt-release-early settings to configure
  // pgpPublicRing := file("./travis/local.pubring.asc"),
  // pgpSecretRing := file("./travis/local.secring.asc"),
  //    releaseEarlyWith := SonatypePublisher
))

val root =
  project.in(file("."))
    .settings(commonSettings: _*)
    .aggregate(facade, demo)
    .settings(
      name                 := "root",
      // No, SBT, we don't want any artifacts for root.
      // No, not even an empty jar.
      publish              := {},
      publishLocal         := {},
      publishArtifact      := false,
      Keys.`package`       := file(""))

lazy val demo =
  project.in(file("demo"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      scalaJSUseMainModuleInitializer  := true,
      webpackBundlingMode              := BundlingMode.LibraryOnly(),
      webpackDevServerExtraArgs        := Seq("--inline"),
      webpackConfigFile in fastOptJS   := Some(baseDirectory.value / "dev.webpack.config.js"),
      version in webpack               := "4.30.0",
      version in webpackCliVersion     := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
      libraryDependencies    ++= Seq(
        "org.typelevel"                     %%% "cats-core"   % "2.0.0"
        // "com.github.japgolly.scalajs-react" %%% "ext-cats"    % "1.6.0"
        // "com.github.japgolly.scalajs-react" %%% "ext-cats"         % scalaJsReact,
        // "org.querki"                        %%% "jquery-facade"    % "1.2",
        // "org.querki"                        %%% "querki-jsext"     % "0.8"
      ),
      npmDependencies in Compile      ++= Seq(
        "jquery"                  -> jQuery,
        // "semantic-ui"             -> semanticUI,
        // "semantic-ui-dropdown"    -> semanticUI,
        // "semantic-ui-modal"       -> semanticUI,
        // "semantic-ui-progress"    -> semanticUI,
        // "semantic-ui-popup"       -> semanticUI,
        // "semantic-ui-tab"         -> semanticUI,
        // "semantic-ui-visibility"  -> semanticUI,
        // "semantic-ui-transition"  -> semanticUI,
        // "semantic-ui-dimmer"      -> semanticUI,
        // "semantic-ui-less"        -> semanticUI

      ),
      // don't publish the demo
      publish                          := {},
      publishLocal                     := {},
      publishArtifact                  := false,
      Keys.`package`                   := file("")
    )
    .dependsOn(facade)

lazy val facade =
  project.in(file("facade"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)
    .settings(
      name                             := "facade",
      moduleName                       := "scalajs-react-beautiful-dnd",
      npmDependencies in Compile       ++= Seq(
        "react"               -> reactJS,
        "react-dom"           -> reactJS,
        "react-beautiful-dnd" -> resactBeautiulDnD
      ),
      npmDevDependencies in Compile ++= Seq(
        "css-loader"                         -> "1.0.0",
        "style-loader"                       -> "0.23.0"
      ),
      // Requires the DOM for tests
      requireJsDomEnv in Test          := true,
      // Use yarn as it is faster than npm
      useYarn                          := true,
      version in webpack               := "4.30.0",
      version in webpackCliVersion     := "3.3.2",
      version in startWebpackDevServer := "3.3.1",
      scalaJSUseMainModuleInitializer  := false,
      // Compile tests to JS using fast-optimisation
      scalaJSStage in Test             := FastOptStage,
      libraryDependencies    ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core"       % scalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test"       % scalaJsReact % "test",
        "io.github.cquiroz.react"           %%% "common"     % "0.5.1",
        "io.github.cquiroz.react"           %%% "test"       % "0.5.1" % Test,
        // "io.github.cquiroz.react"           %%% "cats"       % "0.2.5",
        // "com.lihaoyi"                       %%% "utest"      % "0.7.1" % Test,
        // "org.typelevel"                     %%% "cats-core"  % "1.6.1" % Test
      ),
      webpackConfigFile in Test       := Some(baseDirectory.value / "test.webpack.config.js"),
      testFrameworks                  += new TestFramework("utest.runner.Framework")
    )

lazy val commonSettings = Seq(
  scalaVersion            := scalaVersion.value,
  organization            := "io.github.rpiaggio.react",
  sonatypeProfileName     := "io.github.rpiaggio",
  description             := "scala.js facade for react-beautiful-dnd",
  homepage                := Some(url("https://github.com/rpiaggio/scalajs-react-beautiful-dnd")),
  licenses                := Seq("BSD 3-Clause License" -> url("https://opensource.org/licenses/BSD-3-Clause")),
  publishArtifact in Test := false,
  publishMavenStyle       := true,
  scalacOptions           := Seq(
    "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
    "-encoding", "utf-8",                // Specify character encoding used by source files.
    "-explaintypes",                     // Explain type errors in more detail.
    "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
    "-language:higherKinds",             // Allow higher-kinded types
    "-language:implicitConversions",     // Allow definition of implicit functions called views
    "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
    "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
    // "-Xfuture",                          // Turn on future language features.
    "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
    // "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
    "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
    "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
    "-Xlint:option-implicit",            // Option.apply used implicit view.
    "-Xlint:package-object-classes",     // Class or object defined in package object.
    "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
    // "-Xlint:unsound-match",              // Pattern match may not be typesafe.
    // "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
    // "-Ypartial-unification",             // Enable partial unification in type constructor inference
    "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
    // "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
    // "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
    // "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
    // "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
    "-Ywarn-numeric-widen",              // Warn when numerics are widened.
    "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals",              // Warn if a local definition is unused.
    // "-Ywarn-unused:params",              // Warn if a value parameter is unused.
    // "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates",            // Warn if a private member is unused.
    "-Ywarn-value-discard",              // Warn when non-Unit expression results are unused.
    //      "-Ywarn-dead-code",
    "-P:scalajs:sjsDefinedByDefault",
    "-Yrangepos"
  ),
)
