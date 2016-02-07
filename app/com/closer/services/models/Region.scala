package com.closer.services.models

import play.api.libs.json.Json._
import play.api.libs.json.{JsString, Json}



case class Region(name: String = null, description: String = null){

  override def toString: String = {
    prettyPrint(
      Json.obj(
        "name" -> JsString(name),
        "description" -> JsString(description)
      ))
  }
}

object Region {
  implicit val jsValue2Region= reads[Region]
  implicit val region2jsValue= writes[Region]
}
