package config

import javax.inject.Inject

import com.mongodb.ServerAddress
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoDB
import play.api.{Configuration, Logger}

class Mongo @Inject()(configuration: Configuration) {

  val logger = Logger(this.getClass)

  val host: String = configuration.getString("mongo.host").getOrElse("127.0.0.1")

  val port: Int = configuration.getInt("mongo.port").getOrElse(27017)

  val db: String = configuration.getString("mongo.dbname").getOrElse("test")

  val name: String = configuration.getString("mongo.user").getOrElse(null)

  val pass: String = configuration.getString("mongo.password").getOrElse(null)

  logger.info("============= MONGO CONNECTION INFO ===========")
  logger.info(s"host : $host")
  logger.info(s"port : $port")
  logger.info(s"db : $db")
  logger.info(s"name : $name")
  logger.info(s"pass : $pass")

  val mongodb: MongoDB = {
    val server = new ServerAddress(host, port)
    val client = if (name == null || pass == null)
      nonSecureClient(server)
    else
      secureClient(server)
    client(db)
  }

  def nonSecureClient(server: ServerAddress) = {
    MongoClient(server)
  }

  def secureClient(server: ServerAddress) = {
    val credentials = MongoCredential.createCredential(name, db, pass.toCharArray)
    MongoClient(server, List(credentials))
  }

  override def toString = s"$host:$port/$db"
}