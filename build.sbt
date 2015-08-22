import com.typesafe.sbt.SbtAspectj._

name := """activator-akka-kamon"""

version := "2.3.10"

scalaVersion := "2.11.7"

resolvers += "Kamon Repository Snapshots" at "http://snapshots.kamon.io"

val kamonVersion = "0.5.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.10",
  "io.kamon" %% "kamon-core" % kamonVersion,
  "io.kamon" %% "kamon-statsd" % kamonVersion,
  "io.kamon" %% "kamon-log-reporter" % kamonVersion,
  "io.kamon" %% "kamon-system-metrics" % kamonVersion,
  "org.aspectj" % "aspectjweaver" % "1.8.5"
)

aspectjSettings

javaOptions <++= AspectjKeys.weaverOptions in Aspectj

fork in run := true
