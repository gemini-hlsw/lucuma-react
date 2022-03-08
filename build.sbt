ThisBuild / tlBaseVersion := "0.0"

val scalaJsReactV    = "2.0.1"
val catsV            = "2.7.0"
val munitV           = "0.7.29"
val disciplineMunitV = "1.0.9"

val Scala3 = "3.1.1"
ThisBuild / crossScalaVersions += Scala3

lazy val root = project
  .in(file("."))
  .enablePlugins(NoPublishPlugin)
  .aggregate(common, cats, test)

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
    Test / requireJsDomEnv   := true,
    useYarn                  := true,
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
