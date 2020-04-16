val scalaJSVersion =
  Option(System.getenv("SCALAJS_VERSION")).getOrElse("0.6.32")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % scalaJSVersion)

addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler-sjs06" % "0.17.0")

resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")

addSbtPlugin("org.scalablytyped" % "sbt-scalablytyped" % "202002201029")