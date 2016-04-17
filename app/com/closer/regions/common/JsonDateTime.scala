package com.closer.regions.common

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.{JsString, JsValue, Reads, Writes}

/**
  * Created by rudkodm on 4/17/16.
  */
trait JsonDateTime {

  val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

  val dateTimeeReads = Reads[DateTime](json =>
    json.validate[String].map[DateTime](dtString =>
      DateTime.parse(dtString, DateTimeFormat.forPattern(dateFormat))
    )
  )

  val dateTimeWrites: Writes[DateTime] = new Writes[DateTime] {
    def writes(d: DateTime): JsValue = JsString(d.toString())
  }
}

object JsonDateTime extends JsonDateTime
