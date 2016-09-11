package services.convertion

import data.entities.RuleEntity
import model.Rule

object RuleFormat extends ConversionFormat[Rule, RuleEntity]{

  override implicit def apply(o: Rule): RuleEntity = RuleEntity(
    description = o.description
  )

  override implicit def unapply(o: RuleEntity): Rule = Rule(
    description = o.description
  )

}