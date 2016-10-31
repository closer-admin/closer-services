package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.ServiceProviderEntity
import data.storages.ServiceProviderStorage
import model.ServiceProvider
import services.ServiceProviderService
import services.convertion.ServiceProviderFormat

@Singleton
class ServiceProviderServiceImpl @Inject()(val services: ServiceProviderStorage) extends ServiceProviderService {

  import ServiceProviderFormat._

  def all(): Seq[ServiceProvider] = services.all()

  def save(service: ServiceProvider): ServiceProvider = {
    val servicesEntity: ServiceProviderEntity = ServiceProviderFormat.apply(service)
    services.save(servicesEntity)
    ServiceProviderFormat.unapply(servicesEntity)
  }

  def update(id: String, ServiceProvider: ServiceProvider): ServiceProvider = {
    val ServiceProviderEntity: ServiceProviderEntity = ServiceProviderFormat.apply(ServiceProvider)
    services.update(id, ServiceProviderEntity)
    ServiceProviderFormat.unapply(ServiceProviderEntity)
  }

  def getById(id: String): Option[ServiceProvider] = services.findById(id)

  def getPyProfileId(id: String): Option[ServiceProvider] = services.findByProfileId(id)

  def removeById(id: String): Unit = services.removeById(id)
}



