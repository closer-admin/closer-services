package controllers

import model.Region
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import play.api.http.{ContentTypes, HeaderNames}
import services.RegionService
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import scala.concurrent.Future

class RegionControllerSpec extends FlatSpec with MockFactory with JsonTestCommon with Matchers {

  val service = mock[RegionService]
  val controller = new RegionController(service)

  "all()" should "return Json representation of Region" in {
    (service.all _) expects() returning Seq(
      Region(name = regionName)
    )

    val json = contentAsJson(
      controller.all() apply FakeRequest()
    )

    >>(json(0) \ "name") should be equals regionName
  }

  "all()" should "return FailedRS when exception was thrown" in {
    (service.all _) expects() throwing new NullPointerException()

    val json = contentAsJson(
      controller.all() apply FakeRequest()
    )

    >>(json \ "result") should be equals "failure"
  }

  "getById()" should "return fetch Region by ID and return it as JSON" in {
    (service.getById _) expects(regionId) returning Some(
      Region(id = Some(regionId), name = regionName)
    )

    val json = contentAsJson(
      controller.getById(regionId) apply FakeRequest()
    )

    >>(json \ "id") should be equals regionId
    >>(json \ "name") should be equals regionName
  }

  "removeById()" should "Remove region by ID return SuccessRS" in {
    (service.removeById _) expects(regionId)

    val json = contentAsJson(
      controller.removeById(regionId) apply FakeRequest()
    )

    >>(json \ "result") should be equals "success"
  }

  "save()" should "Store Region and return it representation as JSON" ignore {
    ???
  }

  val regionName = "some region name"
  val regionId = "123"
  val region = Region(id = Some(regionId), name = regionName)
}
