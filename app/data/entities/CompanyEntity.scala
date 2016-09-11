package data.entities

import com.novus.salat.annotations._
import org.bson.types.ObjectId


case class CompanyEntity(
                         @Key("_id") id: ObjectId,
                         name: String,
                         description: Option[String] = None,
                         contactDetails: Option[ContactDetailsEntity] = None
                       ) extends BaseEntity

