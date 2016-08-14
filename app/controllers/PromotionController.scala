package controllers

import javax.inject.{Inject, Singleton}

import model.Promotion
import play.api.mvc._
import services.PromotionService

@Singleton
class PromotionController @Inject()(val promotions: PromotionService) extends Controller with ControllerTemplates {

  def all = ActionTemplate { request =>
    promotions.all()
  }

  def allOfRegion(regionId: String) = ActionTemplate { request =>
    promotions.allOfRegion(regionId)
  }

  def save(regionId: String) = SaveActionTemplate { promo: Promotion =>
        promotions.save(regionId, promo)
  }

  def removeById(regionId: String, id: String) = ActionTemplate { request =>
    promotions.removeById(regionId, id)
    SuccessRS
  }

  def getById(regionId: String, id: String) = ActionTemplate { request =>
    promotions.getById(regionId, id)
  }
}
