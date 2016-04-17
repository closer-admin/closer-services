package com.closer.regions.model

import play.api.libs.json.Json

/**
  * Created by rudkodm on 4/17/16.
  */
case class Rule(
                 description: String
               )

object Rule {
  implicit val object2Json = Json.writes[Rule]
  implicit val json2object = Json.reads[Rule]
}