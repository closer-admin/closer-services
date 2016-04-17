package com.closer.regions.data.dao

import javax.inject.{Inject, Singleton}

import com.closer.regions.config._
import com.closer.regions.data.entities.RegionEntity
import com.mongodb.casbah.Imports._

@Singleton
class RegionMongoDao @Inject()(mongo: Mongo) extends RegionDao {

  val collection = mongo.mongodb("test")

  override def getAll(): List[RegionEntity] = {
    collection.find().map(o =>
      RegionEntity(
        o.get("_id").toString,
        o.as[String]("name"),
        o.as[String]("description"),
        null,
        Set()
      )
    ).toList
  }

  override def insert(r: RegionEntity): Unit = {
    collection.insert(MongoDBObject(
      "name" -> r.name,
      "description" -> r.description
    ))
  }
}