package config

import com.google.inject.AbstractModule
import data.storages.impl.{CompanyMongoStorage, PromotionMongoStorage, RegionMongoStorage, ServiceProviderMongoStorage}
import data.storages.{CompanyStorage, PromotionStorage, RegionStorage, ServiceProviderStorage}
import services.impl.{CompanyServiceImpl, PromotionServiceImpl, RegionServiceImpl, ServiceProviderServiceImpl}
import services.{CompanyService, PromotionService, RegionService, ServiceProviderService}

class DefaultModule extends AbstractModule {
  def configure() = {
    bind(classOf[RegionService]).to(classOf[RegionServiceImpl])
    bind(classOf[PromotionService]).to(classOf[PromotionServiceImpl])
    bind(classOf[CompanyService]).to(classOf[CompanyServiceImpl])
    bind(classOf[ServiceProviderService]).to(classOf[ServiceProviderServiceImpl])
    bind(classOf[RegionStorage]).to(classOf[RegionMongoStorage])
    bind(classOf[PromotionStorage]).to(classOf[PromotionMongoStorage])
    bind(classOf[CompanyStorage]).to(classOf[CompanyMongoStorage])
    bind(classOf[ServiceProviderStorage]).to(classOf[ServiceProviderMongoStorage])
  }
}