name := "Sophi_onboarding_jing2"
 
version := "1.0" 
      
lazy val `sophi_onboarding_jing2` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc ,
  ehcache ,
  ws ,
  specs2 % Test ,
  guice,
  "joda-time" % "joda-time" % "2.9.9",
  "org.joda" % "joda-convert" % "1.8.1",
  "com.opencsv" % "opencsv" % "3.8"

)


//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

import com.typesafe.sbt.packager.MappingsHelper._
mappings in Universal ++= directory(baseDirectory.value / "public")
