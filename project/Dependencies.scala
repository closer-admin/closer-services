import sbt._

object Dependencies{

  val mongoDriver =  "org.mongodb" %% "casbah" % "3.1.0"
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.1" % "test"
  val scalaTestPlay = "org.scalatestplus" %% "play" % "1.4.0" % "test"
  val mockito = "org.mockito" % "mockito-core" % "1.10.19" % "test"

}