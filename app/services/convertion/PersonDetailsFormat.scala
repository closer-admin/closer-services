package services.convertion

import data.entities.PersonDetailsEntity
import model.PersonDetails

object PersonDetailsFormat extends ConversionFormat[PersonDetails, PersonDetailsEntity] {

  override implicit def apply(o: PersonDetails): PersonDetailsEntity = {
    PersonDetailsEntity(
      name = o.name,
      surname = o.surname
    )
  }

  override implicit def unapply(o: PersonDetailsEntity): PersonDetails = {
    PersonDetails(
      name = o.name,
      surname = o.surname
    )
  }
}
