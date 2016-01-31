name := "closer"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.5"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

herokuAppName in Compile := "dry-bastion-13599"

fork in run := true