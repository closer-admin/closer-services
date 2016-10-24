package services

import model.ServiceProvider

trait ServiceProviderService {

  def all(): Seq[ServiceProvider]

  def save(service: ServiceProvider): ServiceProvider

  def update(id: String, service: ServiceProvider): ServiceProvider

  def getById(id: String): Option[ServiceProvider]

  def getPyProfileId(id: String): Option[ServiceProvider]

  def removeById(id: String): Unit
}
