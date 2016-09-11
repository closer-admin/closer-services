package data.storages

import data.entities.CompanyEntity

trait CompanyStorage {

  def all(): Seq[CompanyEntity]

  def save(company: CompanyEntity): Unit

  def update(id: String, company: CompanyEntity): Unit

  def removeById(id: String): Unit

  def findById(id: String): Option[CompanyEntity]

  def removeAll(): Unit

}
