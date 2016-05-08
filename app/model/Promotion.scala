package model

import model.format.DateTimeFormat
import org.joda.time.DateTime
import play.api.libs.json._

case class Promotion(
                      id: Option[String] = None,
                      serviceId: String,
                      promoCode: Option[String] = None,
                      media: Option[String] = None,
                      title: Option[String] = None,
                      expirationDateTime: Option[DateTime] = None,
                      shortDescription: Option[String] = None,
                      fullDescription: Option[String] = None,
                      rule: Option[Rule] = None
                    )

object Promotion {
  implicit val jodaJsonFormat = DateTimeFormat
  implicit val promotionJsonFormat = Json.format[Promotion]
}