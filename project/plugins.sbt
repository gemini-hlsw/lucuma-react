val sbtLucumaVersion = "0.10.12"
addSbtPlugin("edu.gemini"     % "sbt-lucuma-lib"        % sbtLucumaVersion)
addSbtPlugin("com.armanbilge" % "sbt-scalajs-importmap" % "0.1.0")

libraryDependencies += "edu.gemini" %% "lucuma-jsdom" % sbtLucumaVersion

libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
