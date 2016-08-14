package data.storages

import data.entities.RegionEntity
import org.bson.types.ObjectId
import org.scalatest._

class RegionsMongoStorageSpec extends FlatSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll with LocalMongo {

  val regions = injector.instanceOf[RegionStorage]

  before {
    regions.removeAll()
  }

  override def afterAll() {
    regions.removeAll()
  }

  "all()" should "return one saved region" in new LocalMongo {
    regions.save(someRegion)

    regions.all should have size 1
  }

  "save()" should "store Region in DB without exceptions" in new LocalMongo {
    regions.save(someRegion)
  }

  "findById()" should "return previously stored Region by ID" in new LocalMongo {
    val region = someRegion
    val regionId = region.id.toHexString

    regions.save(region)

    regions.findById(regionId) should be equals region
  }

  "removeById()" should "remove previously stored Region by ID" in new LocalMongo {
    val region = someRegion
    val regionId: String = region.id.toHexString

    regions.save(region)
    regions.removeById(regionId)

    regions.findById(regionId) should be equals None
  }

  val someRegionName = "some region name"

  def someOID = new ObjectId()

  def someRegion: RegionEntity = RegionEntity(
    id = someOID,
    name = someRegionName
  )
}
