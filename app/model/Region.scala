package model

import play.api.libs.json._

case class Region(
                   id: Option[String] = None,
                   name: String,
                   description: Option[String] = None,
                   zone: Option[Zone] = None
                 )

object Region {
  implicit val regionJsonFormat = Json.format[Region]
}