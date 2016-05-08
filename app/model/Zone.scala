package model

import play.api.libs.json.Json

case class Zone(
                 center: Location,
                 radius: Double
               )

object Zone {
  implicit val zoneJsonFormat = Json.format[Zone]
}