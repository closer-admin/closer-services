import com.typesafe.config._
import sbt._

object Utils{

  val conf = ConfigFactory
    .parseFile(file("conf/application.conf"))
    .resolve()

  def $(name:String):String = {
    conf.getString(name)
  }
}