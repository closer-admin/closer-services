package controllers

import play.api.Logger
import play.api.libs.json.Json._
import play.api.libs.json.{Format, Json, Writes}
import play.api.mvc.{Action, BodyParsers, Request, _}

import scala.util.{Failure, Success, Try}

trait ControllerTemplates { controller: Controller =>

  val logger = Logger(controller.getClass)

  val RS = (s: String) => Json.obj("result" -> s)
  val SuccessRS = RS("success")
  val FailureRS = RS("failure")

  def ActionTemplate[T](f: (Request[Any] => T))(implicit wr: Writes[T]) = Action { request =>
    Try {
      toJson {
        wr.writes(f(request))
      }
    } match {
      case Success(result) => Ok(result)
      case Failure(ex) =>
        logger.error("Error during processing", ex)
        InternalServerError(FailureRS)
    }
  }

  def SaveActionTemplate[T](saveAndReturnUpdated:(T => T))(implicit format: Format[T]) = Action(BodyParsers.parse.json) { request =>
    val validate = request.body.validate[T]
    validate.fold(
      errors => {
        logger.error(errors.toString())
        BadRequest(FailureRS)
      },
      obj => {
        Ok(Json.toJson(saveAndReturnUpdated(obj)))
      }
    )
  }

  def ParseRequestTemplate[Tin, Tout](f:(Tin => Tout))(implicit formatIn: Format[Tin], formatOut: Format[Tout]) = Action(BodyParsers.parse.json) { request =>
    val validate = request.body.validate[Tin]
    validate.fold(
      errors => {
        logger.error(errors.toString())
        BadRequest(FailureRS)
      },
      obj => {
        Ok(Json.toJson(f(obj)))
      }
    )
  }
}