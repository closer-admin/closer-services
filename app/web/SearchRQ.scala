package web

import model.Location
import play.api.libs.json.Json

case class SearchRQ(
                                center: Location,
                                radius: Option[Double] = None,
                                num: Option[Int] = None
                              )

object SearchRQ {
  implicit val zoneJsonFormat = Json.format[SearchRQ]
}
