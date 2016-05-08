package model

import play.api.libs.json.Json

case class Rule(
                 description: String
               )

object Rule {
  implicit val ruleJsonFormat = Json.format[Rule]
}