package com.closer.services.controllers

import com.closer.services.dao.RegionDao
import com.closer.services.models.Region
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by rudkodm on 2/9/16.
  */
class RegionControllerSpec extends PlaySpec with MockitoSugar with JsonTestCommon {

  val mockDao: RegionDao = mock[RegionDao]
  val controller = new RegionController(mockDao)

  "getAllRegions" when {
    "1 region object returned from DAO" should {
      "return JSON RS with 1 valid Region" in {
        when(mockDao.getAll()) thenReturn List(
          Region(someRegionName, someRegionDescription)
        )

        val json = contentAsJson(
          controller.getAllRegions() apply FakeRequest()
        )

        $(json(0) \ "name") mustBe JsString(someRegionName)
        $(json(0) \ "description") mustBe JsString(someRegionDescription)
      }

    }
  }



  val someRegionName: String = "Some Region Name"
  val someRegionDescription: String = "Some Region Description"
}
