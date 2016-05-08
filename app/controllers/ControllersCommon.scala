package controllers

import play.api.Logger
import play.api.http.{ContentTypes, HeaderNames, HttpProtocol, Status}
import play.api.libs.json.Json._
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{Action, Request, _}

import scala.util.{Failure, Success, Try}

trait ControllersCommon { controller: Controller =>

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
}