package controllers

import org.scalatestplus.play.PlaySpec


/**
  * Created by rudkodm on 2/9/16.
  */
class RegionControllerSpec extends PlaySpec with JsonTestCommon {

//  val mockDao: RegionStorage = mock[RegionStorage]
//  val controller = new RegionController(mockDao)

  "getAllRegions" when {
    "1 region object returned from DAO" should {
      "return JSON RS with 1 valid Region" in {
//        when(mockDao.all()) thenReturn List(
//          Region(someRegionName, someRegionDescription)
//        )
//
//        val json = contentAsJson(
//          controller.getAllRegions() apply FakeRequest()
//        )
//
//        $(json(0) \ "name") mustBe JsString(someRegionName)
//        $(json(0) \ "description") mustBe JsString(someRegionDescription)
      }

    }
  }



  val someRegionName: String = "Some Region Name"
  val someRegionDescription: String = "Some Region Description"
}
