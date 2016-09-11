package data.storages.impl

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.WriteConcern
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import config._
import data.entities.{PromotionEntity, RegionEntity}
import data.storages.PromotionStorage
import data.storages.common.MongoQueryAliaces

@Singleton
class PromotionMongoStorage @Inject()(mongo: Mongo) extends PromotionStorage with MongoQueryAliaces {

  val collection = "promotions"
  val regionLink = "regionId"

  object dao extends SalatDAO[PromotionEntity, String](collection = mongo.mongodb(collection))

  override def all(): Seq[PromotionEntity] = dao.find($o.empty).toSeq

  override def allOfRegion(regionId: String): Seq[PromotionEntity] = {
    dao.find($o(regionLink -> $id(regionId))).toSeq
  }

  override def save(promo: PromotionEntity): Unit = dao.insert(promo)

  override def removeById(id: String): Unit = dao.remove($oid(id))

  override def findById(id: String): Option[PromotionEntity] = dao.findOne($oid(id))

  override def removeAll(): Unit = dao.remove($o.empty)

  override def removeAllOfRegion(regionId: String): Unit = {
    dao.collection.remove($o(regionLink -> $id(regionId)))
  }

  override def update(id: String, promotion: PromotionEntity): Unit = {
    val upsert: Boolean = false
    val multi: Boolean = false
    dao.findOne($oid(id)) foreach { originRegion =>
      dao.update($oid(id), promotion, upsert, multi, WriteConcern.Normal)
    }
  }


}