val sbtLucumaVersion = "0.6.3"
addSbtPlugin("edu.gemini"                  % "sbt-lucuma-lib"         % sbtLucumaVersion)
addSbtPlugin("edu.gemini"                  % "sbt-lucuma-sjs-bundler" % sbtLucumaVersion)
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"          % "1.0.0-beta37+8-3ddfae51-SNAPSHOT")

resolvers += MavenRepository(
  "sonatype-s01-snapshots",
  "https://s01.oss.sonatype.org/content/repositories/snapshots"
)
