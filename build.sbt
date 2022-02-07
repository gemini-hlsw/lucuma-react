ThisBuild / tlBaseVersion       := "0.2"
ThisBuild / tlCiReleaseBranches := Seq("master")

/* ScalablyTyped configuration */
enablePlugins(ScalablyTypedConverterGenSourcePlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val root = project
  .in(file("."))
  .settings(name := "lucuma-svgdotjs")
  .settings(
    crossScalaVersions      := Seq("2.13.6", "3.1.0"),
    // shade into another package
    stOutputPackage         := "lucuma.svgdotjs",
    /* javascript / typescript deps */
    Compile / npmDependencies ++= Seq(
      "@svgdotjs/svg.js" -> "3.0.16"
    ),
    /* disabled because it somehow triggers many warnings */
    scalaJSLinkerConfig ~= (_.withSourceMap(false)),
    // because npm is slow
    useYarn                 := true,
    stSourceGenMode         := SourceGenMode.ResourceGenerator,
    stUseScalaJsDom         := true,
    tlFatalWarnings         := false, // By necessity facades will have unused params
    Compile / doc / sources := Seq(),
    // focus only on these libraries
    stMinimize              := Selection.AllExcept("@svgdotjs/svg.js")
    // stMinimize := Selection.All,
    // stMinimizeKeep ++= List("svgdotjsSvgJs.mod.Element")
  )
  .enablePlugins(ScalablyTypedConverterGenSourcePlugin)
