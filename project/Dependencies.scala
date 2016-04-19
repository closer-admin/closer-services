import sbt._

object Dependencies{

  val mongoDriver =  "org.mongodb" %% "casbah" % "2.7.1"
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.1" % "test"
  val scalaTestPlay = "org.scalatestplus" %% "play" % "1.4.0" % "test"
  val mockito = "org.mockito" % "mockito-core" % "1.10.19" % "test"
  val salat = "com.novus" %% "salat" % "1.9.9"

}