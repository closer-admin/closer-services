package services
import model.{Location, Region}

trait RegionService {

  def all(): Seq[Region]

  def save(region: Region): Region

  def getById(id: String): Option[Region]

  def removeById(id: String): Unit

  def getInZoneRegions(point: Location): Seq[Region]
}
