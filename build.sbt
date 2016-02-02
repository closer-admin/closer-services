import com.typesafe.config._

val conf = ConfigFactory
  .parseFile(file("conf/application.conf"))
  .resolve()



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


/**
 * Helper methods to work with configuration
 */
def $(name:String):String = {
  conf.getString(name)
}

