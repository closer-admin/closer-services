package com.closer.services.dao

import com.closer.services.models.Region

/**
  * Created by rudkodm on 2/8/16.
  */
trait RegionDao {
  def getAll(): List[Region]
  def insert(r: Region): Unit
}
