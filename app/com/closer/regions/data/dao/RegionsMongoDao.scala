package com.closer.regions.data.dao

import com.closer.regions.data.entities.Region

object RegionsMongoDao {
  val f_name = "name"
  val f_description = "description"
}

class RegionsMongoDao(val host:String, val dbName: String, val collectionName: String) {

  private val collection = connect()

  private def connect() = ???

  def insert(region: Region): Unit = ???

  def findAll():List[Region] =  ???

}
