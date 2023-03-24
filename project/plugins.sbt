val sbtLucumaVersion = "0.10.11"
addSbtPlugin("edu.gemini" % "sbt-lucuma-lib" % sbtLucumaVersion)

libraryDependencies += "edu.gemini" %% "lucuma-jsdom" % sbtLucumaVersion

libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
