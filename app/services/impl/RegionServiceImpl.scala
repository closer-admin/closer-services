package services.impl

import javax.inject.{Inject, Singleton}

import data.entities.RegionEntity
import data.storages.RegionStorage
import model.{Location, Region}
import services.RegionService
import services.convertion.RegionFormat

@Singleton
class RegionServiceImpl @Inject()(val regions: RegionStorage) extends RegionService {

  import RegionFormat._

  def all(): Seq[Region] = regions.all()

  def save(region: Region): Region = {
    val regionEntity: RegionEntity = RegionFormat.apply(region)
    regions.save(regionEntity)
    RegionFormat.unapply(regionEntity)
  }

  def update(id: String, region: Region): Region = {
    val regionEntity: RegionEntity = RegionFormat.apply(region)
    regions.update(id, regionEntity)
    RegionFormat.unapply(regionEntity)
  }

  def getById(id: String): Option[Region] = regions.findById(id)

  def removeById(id: String): Unit = regions.removeById(id)

  def getInZoneRegions(point: Location): Seq[Region] = {
    val farRegion: (Region) => Boolean = { r =>
      if (r.zone.isDefined) {
        val z = r.zone.get
        z.radius > z.center.distance(point)
      } else false
    }
    this.all().filter(farRegion)
  }

}



