import com.typesafe.sbt.SbtAspectj._

name := """activator-akka-kamon"""

version := "2.3.12"

scalaVersion := "2.11.7"

val kamonVersion = "0.6.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.8",
  "io.kamon" %% "kamon-core" % kamonVersion,
  "io.kamon" %% "kamon-akka" % kamonVersion,
  "io.kamon" %% "kamon-statsd" % kamonVersion,
  "io.kamon" %% "kamon-log-reporter" % kamonVersion,
  "io.kamon" %% "kamon-system-metrics" % kamonVersion,
  "org.aspectj" % "aspectjweaver" % "1.8.9"
)

aspectjSettings

javaOptions <++= AspectjKeys.weaverOptions in Aspectj

fork in run := true
