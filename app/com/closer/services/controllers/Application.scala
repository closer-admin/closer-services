package com.closer.services.controllers

import com.closer.services.dao.RegionsMongoDao
import com.closer.services.models.Region
import play.api.libs.json._
import play.api.mvc._

object Application extends Controller {

  val dao = new RegionsMongoDao()

  def getAllRegions = Action {
    Ok(Json.toJson(dao.getAll()))
  }

  //curl -H "Content-Type: application/json" -X POST -d '{"name":"RegionXXX","description":"Some description"}' http://localhost:9000/region
  def addRegion = Action(BodyParsers.parse.json) { request =>
    val validate = request.body.validate[Region]
    validate.fold(
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
