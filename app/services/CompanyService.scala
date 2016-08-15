package services

import model.{Location, Company}

trait CompanyService {

  def all(): Seq[Company]

  def save(company: Company): Company

  def update(id: String, Company: Company): Company

  def getById(id: String): Option[Company]

  def removeById(id: String): Unit
}
