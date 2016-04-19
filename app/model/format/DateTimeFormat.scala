package model.format

import org.joda.time.DateTime
import play.api.libs.json._

object DateTimeFormat extends Format[DateTime] {

  val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

  override def writes(o: DateTime): JsValue = Writes.jodaDateWrites(dateFormat).writes(o)

  override def reads(json: JsValue): JsResult[DateTime] = Reads.jodaDateReads(dateFormat).reads(json)
}
