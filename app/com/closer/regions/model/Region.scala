package com.closer.regions.model

import play.api.libs.json.Json
import com.closer.regions.data.entities.RegionEntity

case class Region(
                   id: String,
                   name: String,
                   description: String,
                   zone: Option[Zone],
                   promotions: Set[Promotion] = Set.empty
                 )

object Region {
  implicit val object2Json = Json.writes[Region]
  implicit val json2object = Json.reads[Region]

  implicit def object2Entity(o: Region): RegionEntity = {
    RegionEntity(
      id = o.id,
      name = o.name,
      description = o.description,
      zone = null,
      promotions = Set()
    )
  }
}