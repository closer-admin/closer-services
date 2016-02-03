import com.typesafe.config._
import sbt._

object Utils{

  val conf = ConfigFactory
    .parseFile(file("conf/application.conf"))
    .resolve()

  def $(name:String):String = {
    conf.getString(name)
  }

  def cmd(cmd: String):String = cmd

  def mongo():(String => String) = mongo("localhost", "27017", "test" )

  def mongo(host: String, port: String, name: String):(String => String) = {
    val mongo_instance = s"mongo ${host}:${port}/${name} "
    return {command => mongo_instance + command}
  }

  def mongo(host: String, port: String, name: String, user: String, password: String):(String => String) = {
    val mongo_instance = s"mongo ${host}:${port}/${name}  -u ${user} -p ${password} "
    return {command => mongo_instance + command}
  }

}