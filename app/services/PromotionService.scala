package services

import model.Promotion

trait PromotionService {

  def all(): Seq[Promotion]

  def allOfRegion(regionId: String): Seq[Promotion]

  def save(regionId: String, promo: Promotion): Promotion

  def getById(id: String): Option[Promotion]

  def removeById(id: String): Unit

  def update(id: String, promotion: Promotion): Promotion
  
}
