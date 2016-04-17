package com.closer.regions.model

import play.api.libs.json.Json

/**
  * Created by rudkodm on 4/17/16.
  */
case class Location(
                     latitude: Double,
                     longitude: Double
                   )

object Location {
  implicit val object2Json = Json.writes[Location]
  implicit val json2object = Json.reads[Location]
}