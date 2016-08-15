package model

import play.api.libs.json._

case class Company(
                   id: Option[String] = None,
                   name: String,
                   description: Option[String] = None,
                   contactDetails: Option[ContactDetails] = None,
                   services: Set[Service] = Set.empty
                 )

object Company {
  implicit val regionJsonFormat = Json.format[Company]
}