package data.storages

import data.entities.CompanyEntity
import org.bson.types.ObjectId
import org.scalatest._

class CompaniesMongoStorageSpec extends FlatSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll with LocalMongo {

  val companies = injector.instanceOf[CompanyStorage]

  before {
    companies.removeAll()
  }

  override def afterAll() {
    companies.removeAll()
  }

  "all()" should "return one saved company" in new LocalMongo {
    companies.save(someCompany)

    companies.all should have size 1
  }

  "insert()" should "store Region in DB without exceptions" in new LocalMongo {
    companies.save(someCompany)
  }

  "findById()" should "return previously stored Company by ID" in new LocalMongo {
    val company = someCompany
    val companyId = company.id.toHexString

    companies.save(company)

    companies.findById(companyId) should be equals company
  }

  "removeById()" should "remove previously stored Company by ID" in new LocalMongo {
    val region = someCompany
    val regionId: String = region.id.toHexString

    companies.save(region)
    companies.removeById(regionId)

    companies.findById(regionId) should be equals None
  }

  val someCompanyName = "some company name"

  def someOID = new ObjectId()

  def someCompany: CompanyEntity = CompanyEntity(
    id = someOID,
    name = someCompanyName
  )
}
