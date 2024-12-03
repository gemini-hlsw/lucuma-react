val sbtLucumaVersion = "0.12.2"
addSbtPlugin("edu.gemini"     % "sbt-lucuma-lib"        % sbtLucumaVersion)
addSbtPlugin("com.armanbilge" % "sbt-scalajs-importmap" % "0.1.1")

libraryDependencies += "edu.gemini" %% "lucuma-jsdom" % sbtLucumaVersion

libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
