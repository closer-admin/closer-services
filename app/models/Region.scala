package models

import play.api.libs.json.Json


object Region {

  case class Region(name: String, description: String)

  implicit val region2Json = Json.writes[Region]
  implicit val json2Region = Json.reads[Region]


  var regions = List(Region("Region1", "Some description of region 1"), Region("Region2", "Some description of region 1"))

  def add(b: Region) = regions = regions ::: List(b)
}
