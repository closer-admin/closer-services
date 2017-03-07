package controllers

import javax.inject.{Inject, Singleton}

import model.Promotion
import play.api.mvc._
import services.PromotionService
import web.SearchRQ

@Singleton
class PromotionController @Inject()(val promotions: PromotionService) extends Controller with ControllerTemplates {

  def all = ActionTemplate { request =>
    promotions.all()
  }

  def allOfRegion(regionId: String) = ActionTemplate { request =>
    promotions.allOfRegion(regionId)
  }

  def save() = SaveActionTemplate { promo: Promotion =>
        promotions.save(promo)
  }

  def removeById(id: String) = ActionTemplate { request =>
    promotions.removeById(id)
    SuccessRS
  }

  def getById(id: String) = ActionTemplate { request =>
    promotions.getById(id)
  }

  def getByServiceId(serviceId: String) = ActionTemplate { request =>
    promotions.getByServiceId(serviceId)
  }

  def update(id: String) = SaveActionTemplate { promotion: Promotion=>
    promotions.update(id, promotion)
  }

  def getNearest = ParseRequestTemplate { rq: SearchRQ =>
    promotions.getNearest(rq.center, rq.radius, rq.num)
  }

}
