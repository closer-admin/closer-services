package data.storages

import data.entities.RegionEntity

trait RegionStorage {

  def all(): Seq[RegionEntity]

  def insert(region: RegionEntity): Unit

  def save(region: RegionEntity): Unit

  def removeById(id: String): Unit

  def findById(id: String): Option[RegionEntity]

  def removeAll(): Unit

}
