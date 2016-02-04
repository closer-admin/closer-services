package models

import play.api.libs.json.Json


object Region {

  case class Region(name: String, description: String)

  implicit val region2Json = Json.writes[Region]
  implicit val json2Region = Json.reads[Region]

}
