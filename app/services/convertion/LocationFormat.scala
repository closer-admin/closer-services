package services.convertion

import data.entities.LocationEntity
import model.Location

object LocationFormat extends ConversionFormat[Location, LocationEntity]{

  override implicit def apply(o: Location): LocationEntity = LocationEntity(
    o.latitude,
    o.longitude
  )

  override implicit def unapply(o: LocationEntity): Location = Location(
    o.latitude,
    o.longitude
  )

}