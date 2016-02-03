import sbt._
import Utils._
import Dependencies._

lazy val commonSettings = Seq (
  name := "closer",
  organization := "com.closer",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.10.5"
)

lazy val root = project.in(file("."))
  .enablePlugins(PlayScala)
  .settings(commonSettings)

herokuAppName in Compile := $("heroku.application.name")

resolvers += DefaultMavenRepository

libraryDependencies ++= Seq(
  mongoDriver
)





lazy val mongodb_connection = settingKey[String => String]("Connection to MongoDB. Depends on 'env' variable ")
mongodb_connection := {
  val env = sys.props.getOrElse("env", default = "loc")
  env match {
    case "loc" => mongo()
    case "dev" => mongo(
      ${"mongo.host"},
      ${"mongo.port"},
      ${"mongo.dbname"},
      ${"mongo.user"},
      ${"mongo.password"}
    )
    case _ => mongo()
  }
}

lazy val exec = taskKey[Unit]("Run my command and print result")
exec := {
  val mongodb = mongodb_connection.value
  val command = mongodb apply cmd("--eval db.test.find().pretty().shellPrint()")
  println(command)
  println(command !!)
}




