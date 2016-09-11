package services.convertion

import data.entities.AddressDetailsEntity
import model.AddressDetails

object AddressFormat extends ConversionFormat[AddressDetails, AddressDetailsEntity] {

  override implicit def apply(o: AddressDetails): AddressDetailsEntity = AddressDetailsEntity(
    country = o.country,
    city = o.city,
    address = o.address,
    location = LocationFormat.apply(o.location)
  )

  override implicit def unapply(o: AddressDetailsEntity): AddressDetails = AddressDetails(
    country = o.country,
    city = o.city,
    address = o.address,
    location = LocationFormat.unapply(o.location)
  )

}