package services.convertion

import data.entities.ServiceEntity
import model.Service

object ServiceFormat extends ConversionFormat[Service, ServiceEntity] {

  override implicit def apply(o: Service): ServiceEntity = {
    ServiceEntity(
      id = ObjectIdFormat.applyFromOpt(o.id),
      regionId = ObjectIdFormat.apply(o.regionId),
      addressDetails = AddressFormat.apply(o.addressDetails),
      contactDetails = ContactDetailsFormat.apply(o.contactDetails)
    )
  }

  override implicit def unapply(o: ServiceEntity): Service = {
    Service(
      id = ObjectIdFormat.unapplyToOpt(o.id),
      regionId = ObjectIdFormat.unapply(o.regionId),
      addressDetails = AddressFormat.unapply(o.addressDetails),
      contactDetails = ContactDetailsFormat.unapply(o.contactDetails)
    )
  }
}
