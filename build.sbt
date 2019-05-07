name := "ScalaAkkaHomework"

version := "0.1"

scalaVersion := "2.12.6"

val akkaVersion = "2.5.19"
val akkaHttpVersion = "10.1.8"
val scalaLoggingVersion = "3.9.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.8",
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.22" % Test
)
