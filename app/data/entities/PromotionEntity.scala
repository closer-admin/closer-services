package data.entities

import com.novus.salat.annotations._
import org.bson.types.ObjectId
import org.joda.time.DateTime

case class PromotionEntity(
                            @Key("_id") id: ObjectId,
                            serviceId: ObjectId,
                            regionId: ObjectId,
                            promoCode: Option[String] = None,
                            media: Option[String] = None,
                            title: Option[String] = None,
                            expirationDateTime: Option[DateTime] = None,
                            shortDescription: Option[String] = None,
                            fullDescription: Option[String] = None,
                            rule: Option[RuleEntity] = None
                          ) extends BaseEntity
