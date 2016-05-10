import com.typesafe.config.{Config, ConfigFactory}
import config.DefaultModule
import play.api.ApplicationLoader.Context
import play.api.inject.guice.{GuiceApplicationBuilder, GuiceApplicationLoader}
import play.api.{Configuration, Logger}

class ApplicationLoader extends GuiceApplicationLoader {

  override protected def builder(context: Context): GuiceApplicationBuilder = {

    val envSuffix = sys.props.get("env")
    val config: Config = envSuffix.map(suffix => s"application-${suffix}.conf") match {
      case Some(configFileName) => ConfigFactory.load(configFileName)
      case None => ConfigFactory.empty()
    }
    val envSpecificConfiguration = Configuration(config)

    Logger.info("============= APPLICATION CONFIGURATION ===========")
    Logger.info(s"env : ${envSuffix}")

    initialBuilder
      .in(context.environment)
      .overrides(overrides(context): _*)
      .loadConfig(context.initialConfiguration ++ envSpecificConfiguration)
      .bindings(new DefaultModule())
  }
}
