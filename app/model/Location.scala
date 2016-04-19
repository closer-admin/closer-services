package model

import play.api.libs.json.Json

case class Location(
                     latitude: Double,
                     longitude: Double
                   )

object Location {
  implicit val object2Json = Json.writes[Location]
  implicit val json2object = Json.reads[Location]
}