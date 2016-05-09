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

  override def all(regionId: String): Seq[Promotion] = promotions.of(regionId).all()

  override def removeById(regionId: String, id: String): Unit = promotions.of(regionId).removeById(id)

  override def getById(regionId: String, id: String): Option[Promotion] = promotions.of(regionId).findById(id)

  override def save(regionId: String, promo: Promotion): Promotion = {
    val promoEntity: PromotionEntity = PromotionFormat.apply(promo)
    promotions.of(regionId).save(promoEntity)
    PromotionFormat.unapply(promoEntity)
  }
}



