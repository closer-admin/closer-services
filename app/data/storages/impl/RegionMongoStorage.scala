package data.storages.impl

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.WriteConcern
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import config._
import data.entities.RegionEntity
import data.storages.RegionStorage
import data.storages.common.MongoQueryAliaces

@Singleton
class RegionMongoStorage @Inject()(mongo: Mongo) extends RegionStorage with MongoQueryAliaces {

  private val collection: String = "regions"

  object dao extends SalatDAO[RegionEntity, String](collection = mongo.mongodb(collection))

  override def all(): Seq[RegionEntity] = dao.find($o.empty).toSeq

  override def save(region: RegionEntity): Unit = dao.insert(region)

  override def removeById(id: String): Unit = dao.remove($oid(id))

  override def findById(id: String): Option[RegionEntity] = dao.findOne($oid(id))

  override def removeAll(): Unit = dao.collection.remove($o.empty)

  override def update(id: String, region: RegionEntity): Unit = {
    val upsert: Boolean = false
    val multi: Boolean = false
    dao.findOne($oid(id)) foreach { originRegion =>
      dao.update($oid(id), region, upsert, multi, WriteConcern.Normal)
    }
  }
}