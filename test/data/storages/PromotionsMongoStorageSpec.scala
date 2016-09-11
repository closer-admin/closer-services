package data.storages

import data.entities.{PromotionEntity, RegionEntity}
import org.bson.types.ObjectId
import org.scalatest._

class PromotionsMongoStorageSpec extends FlatSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll with LocalMongo {

  val regionId = new ObjectId()
  val promotions = injector.instanceOf[PromotionStorage]

  before {
    promotions.removeAll()
  }

  override def afterAll() {
    promotions.removeAll()
  }


  "all()" should "fetch 1 Promotion after it was stored there" in new LocalMongo {
    val regionalPromotions = promotions.of(regionId)

    regionalPromotions.save(somePromotion(regionId))

    regionalPromotions.all() should have size 1
  }

  "save()" should "should store Promotion object without throwing exception" in new LocalMongo {
    promotions.of(regionId).save(somePromotion())
  }

  "findById()" should "find previously stored Promotion" in new LocalMongo {
    val regionalPromotions = promotions.of(regionId)
    val promotion = somePromotion()
    val promotionId = promotion.id.toString

    regionalPromotions.save(promotion)

    regionalPromotions.findById(promotionId) should be equals promotion
  }

  "removeById()" should "remove by ID previously stored Promotion" in new LocalMongo {
    val regionalPromotions = promotions.of(regionId)
    val promotion = somePromotion()
    val promotionId = promotion.id.toHexString

    regionalPromotions.save(promotion)
    regionalPromotions.removeById(promotionId)

    regionalPromotions.findById(promotionId) should be equals None
  }

  val someRegionName = "some region name"

  def someOID = new ObjectId()

  def somePromotion(regionOID: ObjectId = someOID): PromotionEntity = PromotionEntity(
    id = someOID,
    regionId = regionOID,
    serviceId = someOID
  )
}
