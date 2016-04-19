package model

import play.api.libs.json.Json

case class Zone(
                 center: Location,
                 radius: Double
               )

object Zone {
  implicit val object2Json = Json.writes[Zone]
  implicit val json2object = Json.reads[Zone]
}