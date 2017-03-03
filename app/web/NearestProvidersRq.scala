package web

import model.Location
import play.api.libs.json.Json

case class NearestProvidersRq(
                                center: Location,
                                radius: Double = 500,
                                num: Int = 10
                              )

object NearestProvidersRq {
  implicit val zoneJsonFormat = Json.format[NearestProvidersRq]
}
