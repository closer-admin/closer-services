package services.convertion

import data.entities.{LocationEntity, ZoneEntity}
import model.{Location, Zone}

object ZoneFormat extends ConverionFormat[Zone, ZoneEntity]{

  override implicit def apply(o: Zone): ZoneEntity = ZoneEntity(
    LocationFormat.apply(o.center),
    o.radius
  )

  override implicit def unapply(o: ZoneEntity): Zone = Zone(
    LocationFormat.unapply(o.center),
    o.radius
  )

}