package data.storages.common

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId

trait MongoQueryAliaces {

  val _id = "_id"

  val $o = MongoDBObject

  def $id = new ObjectId()

  def $id(value: String) = new ObjectId(value)

  def $oid(id: String) = $o(_id -> new ObjectId(id))

  def $oid() = $o(_id -> new ObjectId())

  def  $in[T <: ObjectId](values: Seq[T]) = $o("$in" -> values)

}

object MongoQueryAliaces extends MongoQueryAliaces