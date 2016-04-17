package com.closer.regions.data.dao

import com.closer.regions.data.entities.RegionEntity

/**
  * Created by rudkodm on 2/8/16.
  */
trait RegionDao {
  def getAll(): List[RegionEntity]
  def insert(r: RegionEntity): Unit
}
