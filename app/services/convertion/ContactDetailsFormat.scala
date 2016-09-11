package services.convertion

import data.entities.ContactDetailsEntity
import model.ContactDetails

object ContactDetailsFormat extends ConversionFormat[ContactDetails, ContactDetailsEntity]{

  override implicit def apply(o: ContactDetails): ContactDetailsEntity = ContactDetailsEntity(
    phoneNumber1 = o.phoneNumber1,
    phoneNumber2 = o.phoneNumber2,
    emailAddress = o.emailAddress,
    webSite = o.webSite
  )

  override implicit def unapply(o: ContactDetailsEntity): ContactDetails = ContactDetails(
    phoneNumber1 = o.phoneNumber1,
    phoneNumber2 = o.phoneNumber2,
    emailAddress = o.emailAddress,
    webSite = o.webSite
  )

}