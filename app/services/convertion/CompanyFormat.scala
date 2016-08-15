package services.convertion

import data.entities.CompanyEntity
import model.Company

object CompanyFormat extends ConversionFormat[Company, CompanyEntity] {

  override implicit def apply(o: Company): CompanyEntity = {
    CompanyEntity(
      id = ObjectIdFormat.applyFromOpt(o.id),
      name = o.name,
      description = o.description,
      contactDetails = ContactDetailsFormat.apply(o.contactDetails),
      services = ServiceFormat.apply(o.services)
    )
  }

  override implicit def unapply(o: CompanyEntity): Company = {
    Company(
      id = ObjectIdFormat.unapplyToOpt(o.id),
      name = o.name,
      description = o.description,
      contactDetails = ContactDetailsFormat.unapply(o.contactDetails),
      services = ServiceFormat.unapply(o.services)
    )
  }
}
