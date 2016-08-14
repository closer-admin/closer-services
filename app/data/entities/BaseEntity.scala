package data.entities

import com.novus.salat.annotations._
import org.bson.types.ObjectId

case class BaseEntity(@Key("_id") id: ObjectId) {

  override def hashCode(): Int = {
    val prime = 31
    var result = 1
    result = prime * result + (if (id == null) 0 else id.hashCode)
    return result
  }

  override def equals(that: Any): Boolean = {
    that match {
      case that: RegionEntity => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
  }
}
