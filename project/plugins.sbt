resolvers += MavenRepository("sonatype-s01-snapshots",
                             "https://s01.oss.sonatype.org/content/repositories/snapshots"
)

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"       % "1.0.0-beta37+33-45b800b3-SNAPSHOT")
addSbtPlugin("org.scala-js"                % "sbt-scalajs"         % "1.10.0")
addSbtPlugin("ch.epfl.scala"               % "sbt-scalajs-bundler" % "0.20.0")
addSbtPlugin("org.scalameta"               % "sbt-scalafmt"        % "2.4.6")
addSbtPlugin("edu.gemini"                  % "sbt-lucuma"          % "0.4.4")
addSbtPlugin("com.github.sbt"              % "sbt-ci-release"      % "1.5.10")
