val sbtLucumaVersion = "0.10.10"
addSbtPlugin("edu.gemini" % "sbt-lucuma-lib"         % sbtLucumaVersion)
addSbtPlugin("edu.gemini" % "sbt-lucuma-sjs-bundler" % sbtLucumaVersion)
addSbtPlugin("com.armanbilge" % "sbt-scalajs-linker-bundler" % "0.0.3")
resolvers ++= Resolver.sonatypeOssRepos("releases")
libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"

libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
