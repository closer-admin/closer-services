package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.PromotionEntity
import data.storages.{PromotionStorage, ServiceProviderStorage}
import model.{Location, Promotion, ServiceProvider}
import services.PromotionService
import services.convertion.{ObjectIdFormat, PromotionFormat, ServiceProviderFormat}
import web.PromotionSearchRS

@Singleton
class PromotionServiceImpl @Inject()(val promotions: PromotionStorage, val services: ServiceProviderStorage) extends PromotionService {

  import PromotionFormat._

  val defaultRadius: Double = 500

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

  override def getNearest(center: Location, radius: Option[Double], num: Option[Int]): Seq[PromotionSearchRS] = {

    def distance(s: ServiceProvider): Double = s.addressDetails.location.distance(center)

    val nearestProviders = ServiceProviderFormat.unapply(this.services.all())
      .filter(s => distance(s) <= radius.getOrElse(defaultRadius) )

    val ids = nearestProviders.map(_.id) map {
      case Some(id) => id
    }

    val responses = promotions.findAllByProvidersIds(ids)
      .flatMap { promo =>
        val prov = nearestProviders.find(_.id == ObjectIdFormat.unapplyToOpt(promo.serviceId))
        if (prov.isDefined) Some(new PromotionSearchRS(promo, prov.get))
        else None
      }

    num match {
      case Some(n) => responses.take(n)
      case None => responses.seq
    }
  }
}



