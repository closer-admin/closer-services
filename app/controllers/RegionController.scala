package controllers

import javax.inject.{Inject, Singleton}

import model.Region
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import services.RegionService

@Singleton
class RegionController @Inject()(val regions: RegionService) extends Controller with ControllersCommon {

  def all = ActionTemplate { request =>
    regions.all()
  }

  //curl -H "Content-Type: application/json" -X POST -d '{"name":"Some region name","description":"Some description", "promotions": []' http://localhost:9000/api/regions
  def save = Action(parse.json) { request =>
    val validate = request.body.validate[Region]
    validate.fold(
      errors => {
        logger.error(errors.toString())
        BadRequest(FailureRS)
      },
      region => {
        regions.save(region)
        Ok(Json.toJson(region))
      }
    )
  }

  def removeById(id: String) = ActionTemplate { request =>
    regions.removeById(id)
    SuccessRS
  }

  def getById(id: String) = ActionTemplate { request =>
    regions.getById(id)
  }
}
