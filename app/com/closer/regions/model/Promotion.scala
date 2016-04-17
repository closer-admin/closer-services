package com.closer.regions.model

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import com.closer.regions.common.JsonDateTime._

/**
  * Created by rudkodm on 4/17/16.
  */
case class Promotion(
                      id: String,
                      serviceId: Option[String],
                      promoCode: Option[String],
                      media: Option[String],
                      title: Option[String],
                      expirationDateTime: Option[DateTime],
                      shortDescription: Option[String],
                      fullDescription: Option[String],
                      rule: Option[Rule]
                    )

object Promotion {

  implicit val object2Json: Writes[Promotion] = (
    (JsPath \ "id").write[String] ~
      (JsPath \ "service_id").writeNullable[String] ~
      (JsPath \ "promo_code").writeNullable[String] ~
      (JsPath \ "media").writeNullable[String] ~
      (JsPath \ "title").writeNullable[String] ~
      (JsPath \ "expiration_date_time").writeNullable[DateTime](dateTimeWrites) ~
      (JsPath \ "short_description").writeNullable[String] ~
      (JsPath \ "full_description").writeNullable[String] ~
      (JsPath \ "rule").writeNullable[Rule]
    ) (unlift(Promotion.unapply))

  implicit val json2object: Reads[Promotion] = (
    (JsPath \ "id").read[String] ~
      (JsPath \ "service_id").readNullable[String] ~
      (JsPath \ "promo_code").readNullable[String] ~
      (JsPath \ "media").readNullable[String] ~
      (JsPath \ "title").readNullable[String] ~
      (JsPath \ "expiration_date_time").readNullable[DateTime](dateTimeeReads) ~
      (JsPath \ "short_description").readNullable[String] ~
      (JsPath \ "full_description").readNullable[String] ~
      (JsPath \ "rule").readNullable[Rule]
    ) (Promotion.apply _)
}