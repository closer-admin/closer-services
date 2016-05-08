package services.convertion

import org.bson.types.ObjectId

object ObjectIdFormat extends ConverionFormat[String, ObjectId]{

  override implicit def apply(o: String): ObjectId = new ObjectId(o)

  override implicit def unapply(o: ObjectId): String = o.toHexString

}