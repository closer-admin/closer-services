package services.convertion

import data.entities.RegionEntity
import model.Region
import org.bson.types.ObjectId

object RegionFormat extends ConversionFormat[Region, RegionEntity] {

  override implicit def apply(o: Region): RegionEntity = {
    RegionEntity(
      id = ObjectIdFormat.applyFromOpt(o.id),
      name = o.name,
      description = o.description,
      zone = ZoneFormat.apply(o.zone)
    )
  }

  override implicit def unapply(o: RegionEntity): Region = {
    Region(
      id = ObjectIdFormat.unapplyToOpt(o.id),
      name = o.name,
      description = o.description,
      zone = ZoneFormat.unapply(o.zone)
    )
  }
}
