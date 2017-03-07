package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.PromotionEntity
import data.storages.{PromotionStorage, ServiceProviderStorage}
import model.{Location, Promotion, ServiceProvider}
import services.PromotionService
import services.convertion.{PromotionFormat, ServiceProviderFormat}
import web.PromotionSearchRS

@Singleton
class PromotionServiceImpl @Inject()(val promotions: PromotionStorage, val services: ServiceProviderStorage) extends PromotionService {

  import PromotionFormat._

  override def all(): Seq[Promotion] = promotions.all()

  override def allOfRegion(regionId: String): Seq[Promotion] = promotions.of(regionId).all()

  override def removeById(id: String): Unit = promotions.removeById(id)

  override def getById(id: String): Option[Promotion] = promotions.findById(id)

  override def getByServiceId(id: String): Seq[Promotion] = promotions.findByServiceId(id)

  override def save(promo: Promotion): Promotion = {
    val promoEntity: PromotionEntity = PromotionFormat.apply(promo)
    promotions.save(promoEntity)
    PromotionFormat.unapply(promoEntity)
  }

  override def update(id: String, promotion: Promotion): Promotion = {
    val promotionEntity: PromotionEntity = PromotionFormat.apply(promotion)
    promotions.update(id, promotionEntity)
    PromotionFormat.unapply(promotionEntity)
  }

  override def getNearest(center: Location, radius: Double, num: Int): Seq[PromotionSearchRS] = {
    def distance(s: ServiceProvider) = s.addressDetails.location.distance(center)

    val nearestProviders = ServiceProviderFormat.unapply(this.services.all())
      .filter(s => radius <= distance(s))

    val ids = nearestProviders.map(_.id) map {
      case Some(id) => id
    }

    val responses = PromotionFormat.unapply(promotions.findAllByProvidersIds(ids))
      .map({ promo =>
        val prov = nearestProviders.find(_.id.orNull == promo.serviceId)
        new PromotionSearchRS(promo, prov.orNull)
      })

    responses.take(num)
  }
}



