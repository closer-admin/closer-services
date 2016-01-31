package controllers

import play.api.libs.json._
import play.api.mvc._
import models.Region._

object Application extends Controller {

  def listRegions = Action {
    Ok(Json.toJson(regions))
  }

  def addRegion = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Region]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors)))
      },
      book => {
        add(book)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
}
