package services.convertion

import data.entities.{RuleEntity, ZoneEntity}
import model.{Rule, Zone}

object RuleFormat extends ConverionFormat[Rule, RuleEntity]{

  override implicit def apply(o: Rule): RuleEntity = RuleEntity(
    description = o.description
  )

  override implicit def unapply(o: RuleEntity): Rule = Rule(
    description = o.description
  )

}