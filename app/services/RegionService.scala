package services
import model.Region

trait RegionService {

  def all(): Seq[Region]

  def save(region: Region): Region

  def getById(id: String): Option[Region]

  def removeById(id: String): Unit
}
