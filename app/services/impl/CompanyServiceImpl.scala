package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.CompanyEntity
import data.storages.CompanyStorage
import model.{Location, Company}
import services.CompanyService
import services.convertion.CompanyFormat

@Singleton
class CompanyServiceImpl @Inject()(val companies: CompanyStorage) extends CompanyService {

  import CompanyFormat._

  def all(): Seq[Company] = companies.all()

  def save(Company: Company): Company = {
    val CompanyEntity: CompanyEntity = CompanyFormat.apply(Company)
    companies.save(CompanyEntity)
    CompanyFormat.unapply(CompanyEntity)
  }

  def update(id: String, Company: Company): Company = {
    val CompanyEntity: CompanyEntity = CompanyFormat.apply(Company)
    companies.update(id, CompanyEntity)
    CompanyFormat.unapply(CompanyEntity)
  }

  def getById(id: String): Option[Company] = companies.findById(id)

  def removeById(id: String): Unit = companies.removeById(id)
}



