package model

import data.entities.RegionEntity
import org.bson.types.ObjectId
import play.api.libs.json._

case class Region(
                   id: String,
                   name: String,
                   description: Option[String] = None,
                   zone: Option[Zone] = None,
                   promotions: Set[Promotion] = Set.empty
                 )

object Region {

  implicit val regionJsonFormat = Json.format[Region]

  implicit def object2Entity(o: Region): RegionEntity = {
    RegionEntity(
      id = new ObjectId(o.id),
      name = o.name,
      description = o.description
    )
  }

  implicit def entity2Region(e: RegionEntity): Region = {
    Region(
      id = e.id.toHexString,
      name = e.name,
      description = e.description
    )
  }
}