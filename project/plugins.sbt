resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC13")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.7")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")

addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.11")

classpathTypes += "maven-plugin"
