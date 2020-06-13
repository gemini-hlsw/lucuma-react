/* ScalablyTyped configuration */
enablePlugins(ScalablyTypedConverterGenSourcePlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(Seq(
  homepage := Some(url("https://github.com/gemini-hlsw/gpp-svgdotjs")),
  Global / onChangedBuildSource := ReloadOnSourceChanges
) ++ gspPublishSettings)

lazy val root = project
  .in(file("."))
  .settings(name := "gpp-svgdotjs")
  .settings(
    // shade into another package
    stOutputPackage := "gpp.svgdotjs",
    /* javascript / typescript deps */
    Compile / npmDependencies ++= Seq(
      "@svgdotjs/svg.js" -> "3.0.16"
    ),
    /* disabled because it somehow triggers many warnings */
    scalaJSLinkerConfig ~= (_.withSourceMap(false)),
    // because npm is slow
    useYarn := true,
    stExperimentalEnableImplicitOps := true,
    stUseScalaJsDom := true,
    scalacOptions ~= (_.filterNot(
      Set(
        // By necessity facades will have unused params
        "-Wdead-code",
        "-Wunused:params",
        "-Wunused:imports"
      )
    )),
    sources in (Compile, doc) := Seq(),
    // focus only on these libraries
    stMinimize := Selection.AllExcept("@svgdotjs/svg.js")
  )
  .enablePlugins(ScalablyTypedConverterGenSourcePlugin)
