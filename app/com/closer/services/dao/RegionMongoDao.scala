package com.closer.services.dao

import javax.inject.{Inject, Singleton}

import com.closer.services.config._
import com.closer.services.models.Region
import com.mongodb.casbah.Imports._

@Singleton
class RegionMongoDao @Inject()(mongo: Mongo) extends RegionDao {

  val collection = mongo.mongodb("test")

  override def getAll(): List[Region] = {
    collection.find().map(o =>
      Region(
        o.as[String]("name"),
        o.as[String]("description"))
    ).toList
  }

  override def insert(r: Region): Unit = {
    collection.insert(MongoDBObject(
      "name" -> r.name,
      "description" -> r.description
    ))
  }
}