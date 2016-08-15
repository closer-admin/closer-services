package services.convertion

import data.entities.PromotionEntity
import model.Promotion
import org.bson.types.ObjectId

object PromotionFormat extends ConversionFormat[Promotion, PromotionEntity] {

  override implicit def apply(o: Promotion): PromotionEntity = {
    PromotionEntity(
      id = ObjectIdFormat.applyFromOpt(o.id),
      serviceId = ObjectIdFormat.apply(o.serviceId),
      regionId = ObjectIdFormat.apply(o.regionId),
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
      id = ObjectIdFormat.unapplyToOpt(o.id),
      serviceId = ObjectIdFormat.unapply(o.serviceId),
      regionId = ObjectIdFormat.unapply(o.regionId),
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
