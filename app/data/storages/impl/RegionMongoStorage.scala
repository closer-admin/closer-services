package data.storages.impl

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.WriteConcern
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import config._
import data.entities.RegionEntity
import data.storages.RegionStorage
import data.storages.common.MongoQueryAliaces
import org.bson.types.ObjectId

@Singleton
class RegionMongoStorage @Inject()(mongo: Mongo) extends RegionStorage with MongoQueryAliaces {

  object dao extends SalatDAO[RegionEntity, String](collection = mongo.mongodb("regions"))

  override def all(): Seq[RegionEntity] = {
    dao.find(o.empty).toSeq
  }

  override def insert(region: RegionEntity): Unit = {
    dao.insert(region)
  }

  override def removeById(id: String): Unit = {
    dao.remove($oid(id))
  }

  override def findById(id: String): Option[RegionEntity] = {
    dao.findOne($oid(id))
  }

  override def removeAll(): Unit ={
    dao.collection.remove(o.empty)
  }

  override def update(id: String, region: RegionEntity): Unit = {
    dao.findOne($oid(id)) foreach { originRegion =>
      val updatedRegion = RegionEntity(
        new ObjectId(id),
        region.name,
        region.description,
        region.zone,
        originRegion.promotions
      )
      dao.update($oid(id), updatedRegion, false, false, WriteConcern.Normal)
    }
  }
}