package data.storages

import data.entities.PromotionEntity
import org.bson.types.ObjectId

trait PromotionStorage {
  self =>

  def of(regionId: String): RegionPromotions = new RegionPromotions(regionId)

  def of(regionId: ObjectId): RegionPromotions = new RegionPromotions(regionId.toHexString)

  def all(regionId: String)(): Seq[PromotionEntity]

  def insert(regionId: String)(promo: PromotionEntity): Unit

  def removeById(regionId: String)(id: String): Unit

  def findById(regionId: String)(id: String): Option[PromotionEntity]

  def removeAll(regionId: String)(): Unit


  class RegionPromotions(val regionId: String) {

    def all() = self.all(regionId): Seq[PromotionEntity]

    def insert(promo: PromotionEntity): Unit = self.insert(regionId)(promo)

    def removeById(id: String): Unit = self.removeById(regionId)(id)

    def findById(id: String): Option[PromotionEntity] = self.findById(regionId)(id)

    def removeAll(): Unit = self.removeAll(regionId)
  }

}
