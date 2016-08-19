package services.convertion

import data.entities.ServiceProviderEntity
import model.ServiceProvider

object ServiceProviderFormat extends ConversionFormat[ServiceProvider, ServiceProviderEntity] {

  override implicit def apply(o: ServiceProvider): ServiceProviderEntity = {
    ServiceProviderEntity(
      id = ObjectIdFormat.applyFromOpt(o.id),
      regionId = ObjectIdFormat.apply(o.regionId),
      name = o.name,
      businessCategory = o.businessCategory,
      registerDetails = PersonDetailsFormat.apply(o.registerDetails),
      addressDetails = AddressFormat.apply(o.addressDetails),
      contactDetails = ContactDetailsFormat.apply(o.contactDetails)
    )
  }

  override implicit def unapply(o: ServiceProviderEntity): ServiceProvider = {
    ServiceProvider(
      id = ObjectIdFormat.unapplyToOpt(o.id),
      regionId = ObjectIdFormat.unapply(o.regionId),
      name = o.name,
      businessCategory = o.businessCategory,
      registerDetails = PersonDetailsFormat.unapply(o.registerDetails),
      addressDetails = AddressFormat.unapply(o.addressDetails),
      contactDetails = ContactDetailsFormat.unapply(o.contactDetails)
    )
  }
}
