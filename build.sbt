name := """mycsc"""
organization := "br.isaac"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies ++= Seq(
  jdbc,
  "com.typesafe.play" %% "anorm" % "2.5.1"
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "br.isaac.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "br.isaac.binders._"
