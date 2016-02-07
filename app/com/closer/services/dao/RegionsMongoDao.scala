package com.closer.services.dao
import com.closer.services.config._
import com.closer.services.models.Region
import com.mongodb.casbah.Imports._


class RegionsMongoDao {


  val collection = Mongo.mongodb("test")
  def getAll(): List[Region] = {
    collection.find().map(o =>
      Region(
        o.as[String]("name"),
        o.as[String]("description"))
    ).toList
  }

  def insert(r: Region): Unit = {
    collection.insert(MongoDBObject(
      "name" -> r.name,
      "description" -> r.description
    ))
  }
}


object TestApplication extends App {
  val dao = new RegionsMongoDao()
//  dao.insert(new Region("region1", "Some description"))
//  dao.insert(new Region("region1", "Some description"))
//  dao.insert(new Region("region1", "Some description"))
    dao.getAll().foreach(println)
}