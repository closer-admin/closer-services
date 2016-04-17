package com.closer.regions.data.entities

import com.closer.regions.model.Region


case class RegionEntity(
                         id: String,
                         name: String,
                         description: String,
                         zone: ZoneEntity,
                         promotions: Set[PromotionEntity]
                 )

object RegionEntity {
  implicit def entity2Region(e: RegionEntity): Region = {
    Region(
      id = e.id,
      name = e.name,
      description = e.description,
      zone = None,
      promotions = Set()
    )
  }
}