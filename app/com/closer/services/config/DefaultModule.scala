package com.closer.services.config

import com.closer.services.dao.{RegionDao, RegionMongoDao}
import com.google.inject.AbstractModule

class DefaultModule extends AbstractModule {
  def configure() = {
    bind(classOf[RegionDao]).to(classOf[RegionMongoDao])
  }
}