package model

import java.lang.Math._

import play.api.libs.json.Json

case class Location(
                     latitude: Double,
                     longitude: Double
                   ) {

  def distance(that: Location): Double = {
    val earthRadius = 6371000; //meters

    val dLat = toRadians(that.latitude - this.latitude)
    val dLng = toRadians(that.longitude - this.longitude)
    val a = sin(dLat / 2) * sin(dLat / 2) + cos(toRadians(this.latitude)) * cos(toRadians(that.latitude)) *
      sin(dLng / 2) * sin(dLng / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    earthRadius * c
  }
}

object Location {
  implicit val locationJsonFormat = Json.format[Location]
}