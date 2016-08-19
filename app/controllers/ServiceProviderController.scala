package controllers

import javax.inject.{Inject, Singleton}

import model.ServiceProvider
import play.api.mvc._
import services.ServiceProviderService

@Singleton
class ServiceProviderController @Inject()(val services: ServiceProviderService) extends Controller with ControllerTemplates {

  def all = ActionTemplate { request =>
    services.all()
  }

  def save = SaveActionTemplate { service: ServiceProvider =>
    services.save(service)
  }

  def update(id: String) = SaveActionTemplate { service: ServiceProvider =>
    services.update(id, service)
  }

  def removeById(id: String) = ActionTemplate { request =>
    services.removeById(id)
    SuccessRS
  }

  def getById(id: String) = ActionTemplate { request =>
    services.getById(id)
  }
}
