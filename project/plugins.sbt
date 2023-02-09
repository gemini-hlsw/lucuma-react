val sbtLucumaVersion = "0.10.9"
addSbtPlugin("edu.gemini" % "sbt-lucuma-lib"         % sbtLucumaVersion)
addSbtPlugin("edu.gemini" % "sbt-lucuma-sjs-bundler" % sbtLucumaVersion)

libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
