package model

import play.api.libs.json._

case class ServiceProvider(
                    id: Option[String] = None,
                    regionId: String,
                    profileId: String,
                    name: String,
                    businessCategory: Option[String] = None,
                    addressDetails: AddressDetails,
                    registerDetails: Option[PersonDetails] = None,
                    contactDetails: Option[ContactDetails] = None
                  )

object ServiceProvider {
  implicit val regionJsonFormat = Json.format[ServiceProvider]
}