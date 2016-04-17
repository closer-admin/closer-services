package com.closer.regions.controllers

import com.closer.regions.model.Promotion
import com.closer.regions.model.{Location, Region, Rule, Zone}
import org.joda.time.DateTime
import play.api.libs.json._
import play.api.mvc._

object Application extends Controller {

  def init = Action {
    Ok(Json.obj("status" -> "OK"))
  }

  def listRegions = Action {
    Ok(Json.toJson(generateRegions()))
  }

  def addRegion = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Region]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors)))
      },
      region => {
//        dao.insert(region)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }

  def generateRegions():Seq[Region] = {
    Seq(
      Region(
        id = "1",
        name = "Region 1",
        description = "Descr 1",
        zone = Zone(
          center = Location(
            latitude = 1.0,
            longitude = 1.0
          ),
          radius = 2.0
        ),
        promotions = Set(
          Promotion(
            id = "1",
            serviceId = Some("1"),
            promoCode = Some("123"),
            media = Some("http://example.url"),
            title = Some("Title 1"),
            Some(DateTime.now()),
            shortDescription = Some("Short description 1"),
            fullDescription = Some("full description 1"),
            rule = Some(Rule("some rule description"))
          ),
          Promotion(
            id = "2",
            serviceId = None,
            promoCode = None,
            media = Some("http://example.url"),
            title = Some("Title 2"),
            Some(DateTime.now()),
            shortDescription = None,
            fullDescription = None,
            rule = None
          )
        )
      )
    )
  }
}
