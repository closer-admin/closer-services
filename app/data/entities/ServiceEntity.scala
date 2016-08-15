package data.entities

import com.novus.salat.annotations._
import org.bson.types.ObjectId

case class ServiceEntity(
                          @Key("_id") id: ObjectId,
                          regionId: ObjectId,
                          addressDetails: AddressDetailsEntity,
                          contactDetails: Option[ContactDetailsEntity] = None
                        ) extends BaseEntity
