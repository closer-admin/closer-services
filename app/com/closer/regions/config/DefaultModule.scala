package com.closer.regions.config

import com.closer.regions.data.dao.{RegionDao, RegionMongoDao}
import com.google.inject.AbstractModule

class DefaultModule extends AbstractModule {
  def configure() = {
    bind(classOf[RegionDao]).to(classOf[RegionMongoDao])
  }
}