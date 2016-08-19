package data.storages

import data.entities.ServiceProviderEntity

trait ServiceProviderStorage {

  def all(): Seq[ServiceProviderEntity]

  def save(service: ServiceProviderEntity): Unit

  def update(id: String, service: ServiceProviderEntity): Unit

  def removeById(id: String): Unit

  def findById(id: String): Option[ServiceProviderEntity]

  def removeAll(): Unit

}
