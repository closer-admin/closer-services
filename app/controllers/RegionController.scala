package controllers

import javax.inject.{Inject, Singleton}

import model.{Location, Region}
import play.api.mvc._
import services.RegionService

@Singleton
class RegionController @Inject()(val regions: RegionService) extends Controller with ControllerTemplates {

  def all = ActionTemplate { request =>
    regions.all()
  }

  //curl -H "Content-Type: application/json" -X POST -d '{"name":"Some region name","description":"Some description", "promotions": []' http://localhost:9000/api/regions
  def save = SaveActionTemplate { region: Region =>
    regions.save(region)
  }

  def removeById(id: String) = ActionTemplate { request =>
    regions.removeById(id)
    SuccessRS
  }

  def getById(id: String) = ActionTemplate { request =>
    regions.getById(id)
  }

  def getInZoneRegions = ParseRequestTemplate { location: Location =>
    regions.getInZoneRegions(location);
  }
}
