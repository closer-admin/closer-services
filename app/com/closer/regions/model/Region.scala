package com.closer.regions.model

import play.api.libs.json.Json

case class Region(
                   id: String,
                   name: String,
                   description: String,
                   zone: Zone,
                   promotions: Set[Promotion] = Set.empty
                 )

object Region {
  implicit val object2Json = Json.writes[Region]
  implicit val json2object = Json.reads[Region]
}