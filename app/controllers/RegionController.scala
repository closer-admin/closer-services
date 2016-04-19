package controllers

import javax.inject.{Inject, Singleton}

import data.storages.RegionStorage
import model.Region
import play.api.libs.json._
import play.api.mvc._

@Singleton
class RegionController @Inject()(val regions: RegionStorage) extends Controller {

  def all = Action {
    Ok(
      Json.toJson(
        regions.all().map(Region.entity2Region(_))
      )
    )
  }

  //curl -H "Content-Type: application/json" -X POST -d '{"name":"RegionXXX","description":"Some description"}' http://localhost:9000/region
  def add = Action(BodyParsers.parse.json) { request =>
    val validate = request.body.validate[Region]
    validate.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors)))
      },
      region => {
        regions.insert(region)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }

  def getById(id: String) = Action {
    NoContent
  }

  def removeById(id: String) = Action {
    NoContent
  }

}
