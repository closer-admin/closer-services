package model

import play.api.libs.json._

case class Service(
                    id: Option[String] = None,
                    regionId: String,
                    addressDetails: AddressDetails,
                    contactDetails: Option[ContactDetails] = None
                  )

object Service {
  implicit val regionJsonFormat = Json.format[Service]
}