package data.storages.impl

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.query.Imports._
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import config._
import com.novus.salat.grater
import data.entities.{PromotionEntity, RegionEntity}
import data.storages.PromotionStorage
import data.storages.common.MongoQueryAliaces
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder

@Singleton
class PromotionMongoStorage @Inject()(mongo: Mongo) extends PromotionStorage with MongoQueryAliaces {

  object dao extends SalatDAO[RegionEntity, String](collection = mongo.mongodb("regions"))

  val promotions = "promotions"

  override def all(regionId: String)(): Seq[PromotionEntity] = {
    dao.findOne($oid(regionId)) match {
      case Some(region) => region.promotions.toSeq
      case _ => Seq.empty
    }
  }

  override def save(regionId: String)(promo: PromotionEntity): Unit = {
    val promoObject: DBObject = grater[PromotionEntity].asDBObject(promo)
    dao.update(
      $oid(regionId),
      $push(promotions -> promoObject)
    )
  }

  override def removeById(regionId: String)(promoId: String): Unit = {
    dao.update(
      $oid(regionId),
      $pull(promotions -> $oid(promoId))
    )
  }

  override def findById(regionId: String)(promoId: String): Option[PromotionEntity] = {
    dao.findOne($oid(regionId)) match {
      case Some(region) => region.promotions.find {
        _.id == promoId
      }
      case _ => None
    }
  }

  override def removeAll(regionId: String)(): Unit = {
    dao.update(
      $oid(regionId),
      $set(promotions -> Seq())
    )
  }
}