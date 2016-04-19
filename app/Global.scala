import com.novus.salat.global.{ctx => SalatGlobalContext}
import play.api._

object Global extends GlobalSettings {
  override def onStart(app: Application) = {
    SalatGlobalContext.clearAllGraters()
    SalatGlobalContext.registerClassLoader(Play.classloader(Play.current))
  }
}