package model

import play.api.libs.json.Json

case class Location(
                     latitude: Double,
                     longitude: Double
                   )

object Location {
  implicit val locationJsonFormat = Json.format[Location]
}