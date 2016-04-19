package model

import play.api.libs.json.Json

case class Rule(
                 description: String
               )

object Rule {
  implicit val object2Json = Json.writes[Rule]
  implicit val json2object = Json.reads[Rule]
}