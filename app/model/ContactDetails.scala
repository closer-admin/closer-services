package model

import play.api.libs.json._

case class ContactDetails(
                           phoneNumber1: Option[String] = None,
                           phoneNumber2: Option[String] = None,
                           emailAddress: Option[String] = None,
                           webSite: Option[String] = None
                         )

object ContactDetails {
  implicit val regionJsonFormat = Json.format[ContactDetails]
}