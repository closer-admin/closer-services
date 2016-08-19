package model

import play.api.libs.json._

case class PersonDetails(
                        name: Option[String] = None,
                        surname: Option[String] = None
                  )

object PersonDetails {
  implicit val regionJsonFormat = Json.format[PersonDetails]
}

