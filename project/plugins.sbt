resolvers += MavenRepository("sonatype-s01-snapshots",
                             "https://s01.oss.sonatype.org/content/repositories/snapshots"
)

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"       % "1.0.0-beta36+35-09aed2df-SNAPSHOT")
addSbtPlugin("org.scala-js"                % "sbt-scalajs"         % "1.9.0")
addSbtPlugin("ch.epfl.scala"               % "sbt-scalajs-bundler" % "0.20.0")
addSbtPlugin("com.github.sbt"              % "sbt-ci-release"      % "1.5.10")
addSbtPlugin("org.scalameta"               % "sbt-scalafmt"        % "2.4.6")
addSbtPlugin("io.github.davidgregory084"   % "sbt-tpolecat"        % "0.1.22")
