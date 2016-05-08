package services.convertion

import data.entities.RegionEntity
import model.Region
import org.bson.types.ObjectId

object RegionFormat extends ConverionFormat[Region, RegionEntity] {

  override implicit def apply(o: Region): RegionEntity = {
    RegionEntity(
      id = ObjectIdFormat.apply(o.id) match {
        case Some(id) => id
        case None => new ObjectId()
      },
      name = o.name,
      description = o.description,
      zone = ZoneFormat.apply(o.zone)
    )
  }

  override implicit def unapply(o: RegionEntity): Region = {
    Region(
      id = Some(ObjectIdFormat.unapply(o.id)),
      name = o.name,
      description = o.description,
      zone = ZoneFormat.unapply(o.zone),
      promotions = o.promotions.map { promo => ObjectIdFormat.unapply(promo.id) }
    )
  }
}
