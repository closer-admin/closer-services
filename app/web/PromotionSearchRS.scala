package web

import model.format.DateTimeFormat
import model.{Promotion, Rule, ServiceProvider}
import org.joda.time.DateTime
import play.api.libs.json.Json

case class PromotionSearchRS(
                              id: Option[String] = None,
                              service: Option[ServiceProvider],
                              regionId: String,
                              promoCode: Option[String] = None,
                              media: Option[String] = None,
                              title: Option[String] = None,
                              expirationDateTime: Option[DateTime] = None,
                              shortDescription: Option[String] = None,
                              fullDescription: Option[String] = None,
                              rule: Option[Rule] = None
                              ) {

  def this(promo: Promotion, prov: ServiceProvider) {
    this(
      promo.id,
      Some(prov),
      promo.regionId,
      promo.promoCode,
      promo.media,
      promo.title,
      promo.expirationDateTime,
      promo.shortDescription,
      promo.fullDescription,
      promo.rule)
  }
}

object PromotionSearchRS {
  implicit val jodaJsonFormat = DateTimeFormat
  implicit val zoneJsonFormat = Json.format[PromotionSearchRS]
}


