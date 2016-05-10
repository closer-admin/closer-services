import CommonUtils._
import sbt._

object MongoPlugin extends AutoPlugin {
  import MongoUtils._

  object autoImport {
    lazy val mongoSetting = settingKey[String => String]("Connection to MongoDB. Depends on 'env' variable ")
    lazy val mongoimportSetting = settingKey[(String, String) => String]("Import to MongoDB. Depends on 'env' variable ")
    lazy val mongoexportSetting = settingKey[(String, String) => String]("Export to MongoDB. Depends on 'env' variable ")

    lazy val mongoDrop = taskKey[Unit]("Print all rows in test collection")
    lazy val mongoImport = taskKey[Unit]("Import testdata.json to mongo")
    lazy val mongoExport = taskKey[Unit]("Export testdata.json to mongo")
  }

  import autoImport._

  val collection = $("mongo.collection")

  lazy val mongoSettings: Seq[Def.Setting[_]] = Seq(
    mongoSetting := {
      env match {
        case `envLoc` => mongo()
        case `envDev` => mongo(
          $("mongo.host"),
          $("mongo.port"),
          $("mongo.dbname"),
          $("mongo.user"),
          $("mongo.password")
        )
        case _ => mongo()
      }
    },
    mongoimportSetting := {
      env match {
        case `envLoc` => mongoimport()
        case `envDev` => mongoimport(
          $("mongo.host"),
          $("mongo.port"),
          $("mongo.dbname"),
          $("mongo.user"),
          $("mongo.password")
        )
        case _ => mongoimport()
      }
    },
    mongoexportSetting := {
      env match {
        case `envLoc` => mongoexport()
        case `envDev` => mongoexport(
          $("mongo.host"),
          $("mongo.port"),
          $("mongo.dbname"),
          $("mongo.user"),
          $("mongo.password")
        )
        case _ => mongoexport()
      }
    },
    mongoDrop := {
      val mongo = mongoSetting.value
      val command = mongo apply s"db.${collection}.drop()"
      println(command)
      println(command !!)
    },
    mongoImport := {
      val mongoimport = mongoimportSetting.value
      val command = mongoimport apply(collection, s"testdata/${collection}.json")
      println(command)
      println(command !!)
    },
    mongoExport := {
      val mongoexport = mongoexportSetting.value
      val command = mongoexport apply(collection, s"testdata/${collection}.json")
      println(command)
      println(command !!)
    }
  )

  override lazy val projectSettings = inConfig(Compile)(mongoSettings)
}





object MongoUtils {
  val defaultHost = "localhost"
  val defaultPort = "27017"
  val defaultDb = "test"


  /**
    * mongo
    *
    * @return Return main part of mongo bash command
    */
  def mongo(): (String => String) = mongo(defaultHost, defaultPort, defaultDb)

  def mongo(host: String, port: String, db: String): (String) => String = {
    val bashMongo = s"mongo $host:$port/$db "
    cmd => s"$bashMongo --eval $cmd"
  }


  def mongo(host: String, port: String, db: String, user: String, password: String): (String => String) = {
    val bashMongo = s"mongo $host:$port/$db  -u $user -p $password "
    cmd => s"$bashMongo --eval $cmd"
  }

  /**
    * mongoinport
    *
    * @return Return main part of mongoimport bash command
    */
  def mongoimport(): ((String, String) => String) = mongoimport(defaultHost, defaultPort, defaultDb)

  def mongoimport(host: String, port: String, db: String): ((String, String) => String) = {
    val bashMongoImport = s"mongoimport --host $host --port $port --db $db "
    (collection, file) => s"$bashMongoImport --collection $collection --file $file"
  }

  def mongoimport(host: String, port: String, db: String, user: String, password: String): ((String, String) => String) = {
    val bashMongoImport = s"mongoimport --host $host --port $port --db $db --username $user --password $password "
    (collection, file) => s"$bashMongoImport --collection $collection --file $file"
  }

  /**
    * mongoexport
    *
    * @return Return main part of mongoexport bash command
    */
  def mongoexport(): ((String, String) => String) = mongoexport(defaultHost, defaultPort, defaultDb)

  def mongoexport(host: String, port: String, db: String): ((String, String) => String) = {
    val bashMongoExport = s"mongoexport --host $host --port $port --db $db "
    (collection, out) => s"$bashMongoExport --collection $collection --out $out"
  }

  def mongoexport(host: String, port: String, db: String, user: String, password: String): ((String, String) => String) = {
    val bashMongoExport = s"mongoexport --host $host --port $port --db $db --username $user --password $password "
    (collection, out) => s"$bashMongoExport --collection $collection --out $out"
  }
}