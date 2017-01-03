package data.storages

import data.entities.PromotionEntity
import org.bson.types.ObjectId

trait PromotionStorage {
  self =>

  def of(regionId: String): RegionPromotions = new RegionPromotions(regionId)

  def of(regionId: ObjectId): RegionPromotions = new RegionPromotions(regionId.toString)

  def all(): Seq[PromotionEntity]

  def allOfRegion(regionId: String): Seq[PromotionEntity]

  def save(promo: PromotionEntity): Unit

  def update(id: String, promotion: PromotionEntity): Unit

  def removeById(id: String): Unit

  def findById(id: String): Option[PromotionEntity]

  def findByServiceId(id: String): Seq[PromotionEntity]

  def removeAll(): Unit

  def removeAllOfRegion(regionId: String): Unit


  class RegionPromotions(val regionId: String) {

    def all() = self.allOfRegion(regionId)

    def removeAll(): Unit = self.removeAllOfRegion(regionId)

  }

}
