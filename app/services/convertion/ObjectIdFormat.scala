package services.convertion

import org.bson.types.ObjectId

object ObjectIdFormat extends ConversionFormat[String, ObjectId]{

  override implicit def apply(o: String): ObjectId = new ObjectId(o)

  implicit def applyFromOpt(opt: Option[String]): ObjectId = opt match {
    case Some(id) => id
    case None => new ObjectId()
  }

  override implicit def unapply(o: ObjectId): String = o.toHexString

  implicit def unapplyToOpt(o: ObjectId): Option[String] = if(o != null) Some(o.toHexString) else None

}