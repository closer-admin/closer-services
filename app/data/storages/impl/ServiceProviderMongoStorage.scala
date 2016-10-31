package data.storages.impl

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.WriteConcern
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import config._
import data.entities.ServiceProviderEntity
import data.storages.ServiceProviderStorage
import data.storages.common.MongoQueryAliaces

@Singleton
class ServiceProviderMongoStorage @Inject()(mongo: Mongo) extends ServiceProviderStorage with MongoQueryAliaces {

  private val collection: String = "services"
  private val profileLink: String = "profileId"

  object dao extends SalatDAO[ServiceProviderEntity, String](collection = mongo.mongodb(collection))

  override def all(): Seq[ServiceProviderEntity] = dao.find($o.empty).toSeq

  override def save(service: ServiceProviderEntity): Unit = dao.insert(service)

  override def removeById(id: String): Unit = dao.remove($oid(id))

  override def findById(id: String): Option[ServiceProviderEntity] = dao.findOne($oid(id))

  override def findByProfileId(id: String): Option[ServiceProviderEntity] = dao.findOne($o(profileLink -> id))

  override def removeAll(): Unit = dao.collection.remove($o.empty)

  override def update(id: String, service: ServiceProviderEntity): Unit = {
    val upsert: Boolean = false
    val multi: Boolean = false
    dao.findOne($oid(id)) foreach { originServiceProvider =>
      dao.update($oid(id), service, upsert, multi, WriteConcern.Normal)
    }
  }
}