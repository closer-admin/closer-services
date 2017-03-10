package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.ServiceProviderEntity
import data.storages.ServiceProviderStorage
import model.{Location, ServiceProvider}
import services.ServiceProviderService
import services.convertion.ServiceProviderFormat

@Singleton
class ServiceProviderServiceImpl @Inject()(val services: ServiceProviderStorage) extends ServiceProviderService {

  import ServiceProviderFormat._

  val defaultRadius: Double = 500

  override def all(): Seq[ServiceProvider] = services.all()

  override def save(service: ServiceProvider): ServiceProvider = {
    val servicesEntity: ServiceProviderEntity = ServiceProviderFormat.apply(service)
    services.save(servicesEntity)
    ServiceProviderFormat.unapply(servicesEntity)
  }

  override def update(id: String, ServiceProvider: ServiceProvider): ServiceProvider = {
    val ServiceProviderEntity: ServiceProviderEntity = ServiceProviderFormat.apply(ServiceProvider)
    services.update(id, ServiceProviderEntity)
    ServiceProviderFormat.unapply(ServiceProviderEntity)
  }

  override def getById(id: String): Option[ServiceProvider] = services.findById(id)

  override def getPyProfileId(id: String): Option[ServiceProvider] = services.findByProfileId(id)

  override def removeById(id: String): Unit = services.removeById(id)

  override def getNearest(center: Location, radius: Option[Double], num: Option[Int]): Seq[ServiceProvider] = {

    def distance(s: ServiceProvider): Double = s.addressDetails.location.distance(center)

    val res = this.all()
      .filter(s => distance(s) <= radius.getOrElse(defaultRadius) )
      .sortBy(s => distance(s))

    num match {
      case Some(n) => res.take(n)
      case None => res.seq
    }
  }
}



