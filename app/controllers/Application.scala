package controllers

import dao.RegionsMongoDao
import models.Region._
import play.api.libs.json._
import play.api.mvc._

object Application extends Controller {

  val dao = new RegionsMongoDao("localhost", "test", "test")

  def init = Action {
    Ok(Json.obj("status" -> "OK"))
  }

  def listRegions = Action {
    Ok(Json.obj("status" -> "OK"))
  }

  def addRegion = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Region]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors)))
      },
      region => {
        dao.insert(region)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
}
