package data.entities

import org.bson.types.ObjectId

abstract class BaseEntity() {
  def id: ObjectId

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
