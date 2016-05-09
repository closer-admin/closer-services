package config

import data.storages.{PromotionStorage, RegionStorage}
import com.google.inject.AbstractModule
import data.storages.impl.{PromotionMongoStorage, RegionMongoStorage}
import services.{PromotionService, RegionService}
import services.impl.{PromotionServiceImpl, RegionServiceImpl}

class DefaultModule extends AbstractModule {
  def configure() = {
    bind(classOf[RegionService]).to(classOf[RegionServiceImpl])
    bind(classOf[PromotionService]).to(classOf[PromotionServiceImpl])
    bind(classOf[RegionStorage]).to(classOf[RegionMongoStorage])
    bind(classOf[PromotionStorage]).to(classOf[PromotionMongoStorage])
  }
}