package data.storages.impl

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.WriteConcern
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import config._
import data.entities.CompanyEntity
import data.storages.CompanyStorage
import data.storages.common.MongoQueryAliaces

@Singleton
class CompanyMongoStorage @Inject()(mongo: Mongo) extends CompanyStorage with MongoQueryAliaces {

  private val collection: String = "companies"

  object dao extends SalatDAO[CompanyEntity, String](collection = mongo.mongodb(collection))

  override def all(): Seq[CompanyEntity] = dao.find($o.empty).toSeq

  override def save(company: CompanyEntity): Unit = dao.insert(company)

  override def removeById(id: String): Unit = dao.remove($oid(id))

  override def findById(id: String): Option[CompanyEntity] = dao.findOne($oid(id))

  override def removeAll(): Unit = dao.collection.remove($o.empty)

  override def update(id: String, company: CompanyEntity): Unit = {
    val upsert: Boolean = false
    val multi: Boolean = false
    dao.findOne($oid(id)) foreach { originCompany =>
      dao.update($oid(id), company, upsert, multi, WriteConcern.Normal)
    }
  }
}