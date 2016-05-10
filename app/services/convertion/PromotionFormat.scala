package services.convertion

import data.entities.PromotionEntity
import model.Promotion
import org.bson.types.ObjectId

object PromotionFormat extends ConverionFormat[Promotion, PromotionEntity] {

  override implicit def apply(o: Promotion): PromotionEntity = {
    PromotionEntity(
      id = ObjectIdFormat.apply(o.id) match {
        case Some(id) => id
        case None => new ObjectId()
      },
      serviceId = ObjectIdFormat.apply(o.serviceId),
      promoCode = o.promoCode,
      media = o.media,
      title = o.title,
      expirationDateTime = o.expirationDateTime,
      shortDescription = o.shortDescription,
      fullDescription = o.fullDescription,
      rule = RuleFormat.apply(o.rule)
    )
  }

  override implicit def unapply(o: PromotionEntity): Promotion = {
    Promotion(
      id = Some(ObjectIdFormat.unapply(o.id)),
      serviceId = ObjectIdFormat.unapply(o.serviceId),
      promoCode = o.promoCode,
      media = o.media,
      title = o.title,
      expirationDateTime = o.expirationDateTime,
      shortDescription = o.shortDescription,
      fullDescription = o.fullDescription,
      rule = RuleFormat.unapply(o.rule)
    )
  }
}
