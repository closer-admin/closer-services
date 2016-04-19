import com.typesafe.config._
import java.io.File

object CommonUtils {
  val envSuffix = sys.props.get("env")
  val configFilePath = envSuffix match {
    case Some(suffix) => s"conf/application-${suffix}.conf"
    case None => "conf/application.conf"
  }

  case class Env(configFile: File)
  val env = Env(new File(configFilePath))
  val envLoc = Env(new File("conf/application.conf"))
  val envDev = Env(new File("conf/application-dev.conf"))

  val conf = {
    val confFile = env match {
      case `envLoc` =>  env.configFile
      case `envDev` =>  env.configFile
      case _ => env.configFile
    }
    println(s"[info] Following configuration was loaded: $confFile")
    ConfigFactory.parseFile(confFile).resolve()
  }

  def $(name:String):String = {
    println(s"[info] Get property $name from configuration")
    val value = conf.getString(name)
    println(s"[info] For property $name returned $value ")
    value
  }
}


