resolvers += Resolver.bintrayRepo("oyvindberg", "converter")
resolvers += Resolver.bintrayRepo("oyvindberg", "converter-snapshots")

addSbtPlugin("org.scala-js"                % "sbt-scalajs"         % "1.4.0")
addSbtPlugin("ch.epfl.scala"               % "sbt-scalajs-bundler" % "0.20.0")
addSbtPlugin("org.scalameta"               % "sbt-scalafmt"        % "2.4.2")
addSbtPlugin("edu.gemini"                  % "sbt-lucuma"          % "0.3.4")
addSbtPlugin("com.geirsson"                % "sbt-ci-release"      % "1.5.5")
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"       % "1.0.0-beta29.1")
