package controllers

import javax.inject.{Inject, Singleton}

import model.Company
import play.api.mvc._
import services.CompanyService

@Singleton
class CompanyController @Inject()(val companies: CompanyService) extends Controller with ControllerTemplates {

  def all = ActionTemplate { request =>
    companies.all()
  }

  def save = SaveActionTemplate { company: Company =>
    companies.save(company)
  }

  def update(id: String) = SaveActionTemplate { company: Company =>
    companies.update(id, company)
  }

  def removeById(id: String) = ActionTemplate { request =>
    companies.removeById(id)
    SuccessRS
  }

  def getById(id: String) = ActionTemplate { request =>
    companies.getById(id)
  }
}
