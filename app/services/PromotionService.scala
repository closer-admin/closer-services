package services

import model.{Location, Promotion}
import web.PromotionSearchRS

trait PromotionService {

  def all(): Seq[Promotion]

  def allOfRegion(regionId: String): Seq[Promotion]

  def save(promo: Promotion): Promotion

  def getById(id: String): Option[Promotion]

  def getByServiceId(id: String): Seq[Promotion]

  def removeById(id: String): Unit

  def update(id: String, promotion: Promotion): Promotion

  def getNearest(center: Location, radius: Double, num: Int) : Seq[PromotionSearchRS]

}
