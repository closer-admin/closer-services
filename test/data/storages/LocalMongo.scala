package data.storages

import config.DefaultModule
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder

trait LocalMongo extends {
  val injector = new GuiceApplicationBuilder()
    .configure(Configuration())
    .bindings(new DefaultModule)
    .injector

}
