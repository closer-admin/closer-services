package config

import com.google.inject.AbstractModule
import data.storages.impl.{CompanyMongoStorage, PromotionMongoStorage, RegionMongoStorage}
import data.storages.{CompanyStorage, PromotionStorage, RegionStorage}
import services.impl.{CompanyServiceImpl, PromotionServiceImpl, RegionServiceImpl}
import services.{CompanyService, PromotionService, RegionService}

class DefaultModule extends AbstractModule {
  def configure() = {
    bind(classOf[RegionService]).to(classOf[RegionServiceImpl])
    bind(classOf[PromotionService]).to(classOf[PromotionServiceImpl])
    bind(classOf[CompanyService]).to(classOf[CompanyServiceImpl])
    bind(classOf[RegionStorage]).to(classOf[RegionMongoStorage])
    bind(classOf[PromotionStorage]).to(classOf[PromotionMongoStorage])
    bind(classOf[CompanyStorage]).to(classOf[CompanyMongoStorage])
  }
}