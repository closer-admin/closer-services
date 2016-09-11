package model

import play.api.libs.json.Json

case class AddressDetails(
                    country: Option[String] = None,
                    city: Option[String] = None,
                    address: Option[String] = None,
                    location: Location
                  )

object AddressDetails {
  implicit val ruleJsonFormat = Json.format[AddressDetails]
}