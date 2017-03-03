package services

import model.{Location, ServiceProvider}

trait ServiceProviderService {

  def all(): Seq[ServiceProvider]

  def save(service: ServiceProvider): ServiceProvider

  def update(id: String, service: ServiceProvider): ServiceProvider

  def getById(id: String): Option[ServiceProvider]

  def getPyProfileId(id: String): Option[ServiceProvider]

  def removeById(id: String): Unit

  def getNearest(center: Location, radius: Double, num: Int) : Seq[ServiceProvider]

}
