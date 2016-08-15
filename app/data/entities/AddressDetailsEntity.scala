package data.entities

case class AddressDetailsEntity(
                          country: Option[String] = None,
                          city: Option[String] = None,
                          address: Option[String] = None,
                          location: LocationEntity
                        )
