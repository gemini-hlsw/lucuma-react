resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += MavenRepository("sonatype-s01-snapshots",
                             "https://s01.oss.sonatype.org/content/repositories/snapshots"
)

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"  % "1.0.0-beta36+29-f3b7dd30-SNAPSHOT")
addSbtPlugin("edu.gemini"                  % "sbt-lucuma-lib" % "0.6-cde3ac9-SNAPSHOT")
