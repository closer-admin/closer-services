package data.entities

import com.novus.salat.annotations._
import org.bson.types.ObjectId

case class ServiceProviderEntity(
                          @Key("_id") id: ObjectId,
                          regionId: ObjectId,
                          name: String,
                          businessCategory: Option[String] = None,
                          addressDetails: AddressDetailsEntity,
                          registerDetails: Option[PersonDetailsEntity] = None,
                          contactDetails: Option[ContactDetailsEntity] = None
                        ) extends BaseEntity
