package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.PromotionEntity
import data.storages.PromotionStorage
import model.Promotion
import services.PromotionService
import services.convertion.PromotionFormat

@Singleton
class PromotionServiceImpl @Inject()(val promotions: PromotionStorage) extends PromotionService {

  import PromotionFormat._

  override def all(): Seq[Promotion] = promotions.all()

  override def allOfRegion(regionId: String): Seq[Promotion] = promotions.of(regionId).all()

  override def removeById(id: String): Unit = promotions.removeById(id)

  override def getById(id: String): Option[Promotion] = promotions.findById(id)

  override def save(regionId: String, promo: Promotion): Promotion = {
    val promoEntity: PromotionEntity = PromotionFormat.apply(promo)
    promotions.of(regionId).save(promoEntity)
    PromotionFormat.unapply(promoEntity)
  }

  def update(id: String, promotion: Promotion): Promotion = {
    val promotionEntity: PromotionEntity = PromotionFormat.apply(promotion)
    promotions.update(id, promotionEntity)
    PromotionFormat.unapply(promotionEntity)
  }

}



