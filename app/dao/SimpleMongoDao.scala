package dao

import com.mongodb.casbah.Imports._
import java.util.Date

object SimpleMongoDao {
  def perform():Unit = {
    val collection = MongoClient()("test")("test")
    val o = MongoDBObject(
      "field1" -> "value1",
      "field2" -> new Date(),
      "filed3" -> 3.14
    )
    collection.insert(o)
  }
}

object TestApplication extends App {
  SimpleMongoDao.perform();
}
