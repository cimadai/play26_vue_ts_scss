name := "play2.6-template"

version := "0.1.0.0-SNAPSHOT"

val PROJECT_SCALA_VERSION = "2.11.11"

scalaVersion := PROJECT_SCALA_VERSION

val SLICK_VERSION = "3.0.2"

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val libraries = Seq(
  "com.typesafe.play" %% "play-json" % "2.6.7",
  "com.h2database" % "h2" % "1.4.196",
  "org.postgresql" % "postgresql" % "42.1.4.jre7",
  "org.julienrf" %% "play-jsmessages" % "3.0.0",
  "com.typesafe.play" %% "play-slick" % SLICK_VERSION,
  "com.typesafe.play" %% "play-slick-evolutions" % SLICK_VERSION,
  "com.github.karelcemus" %% "play-redis" % "1.6.1",
  "io.swagger" %% "swagger-play2" % "1.6.0",
  "com.sksamuel.scrimage" %% "scrimage-core" % "2.1.8",
  "com.typesafe.play" %% "play-iteratees" % "2.6.1",
  "com.notnoop.apns" % "apns" % "1.0.0.Beta6",
  guice,
  cacheApi,
  ws,
  filters
)

updateOptions := updateOptions.value.withCachedResolution(true)

import play.sbt.PlayImport.PlayKeys.playRunHooks
import scala.sys.process.Process
val webpackCommand = "webpack"
lazy val webpack = taskKey[Unit]("Run webpack when packaging the application")
def runWebpack(file: File): Int = {
  Process(webpackCommand, file, "BUILD_ENV" -> "production").run().exitValue()
}
webpack := {
  if(runWebpack(baseDirectory.value) != 0) throw new Exception("Something goes wrong when running webpack.")
}
dist := (dist dependsOn webpack).value
stage := (stage dependsOn webpack).value

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    scalacOptions ++= Seq(
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-Xfuture",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused"
    ),
    updateOptions := updateOptions.value.withCachedResolution(true),
    scalaVersion := PROJECT_SCALA_VERSION,
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,
    libraryDependencies ++= libraries
  )
  .settings(
    playRunHooks += RunSubProcess(s"$webpackCommand --progress --colors --watch")
  )
  .enablePlugins(SbtWeb)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "helpers"
  )

