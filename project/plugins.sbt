resolvers += MavenRepository("sonatype-s01-snapshots",
                             "https://s01.oss.sonatype.org/content/repositories/snapshots"
)

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"  % "1.0.0-beta36+29-f3b7dd30-SNAPSHOT")
addSbtPlugin("org.scalameta"               % "sbt-scalafmt"   % "2.4.5")
addSbtPlugin("edu.gemini"                  % "sbt-lucuma"     % "0.4.2")
addSbtPlugin("com.github.sbt"              % "sbt-ci-release" % "1.5.10")
addSbtPlugin("org.scala-js"                % "sbt-scalajs"    % "1.8.0")
