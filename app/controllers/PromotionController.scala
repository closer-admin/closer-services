package controllers

import javax.inject.{Inject, Singleton}

import model.{Promotion, Region}
import play.api.libs.json._
import play.api.mvc._
import services.{PromotionService, RegionService}

@Singleton
class PromotionController @Inject()(val promotions: PromotionService) extends Controller with ControllerTemplates {

  def all(regionId: String) = ActionTemplate { request =>
    promotions.all(regionId)
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
