package com.closer.services.controllers

import play.api.libs.json.{JsDefined, JsLookupResult, JsUndefined, JsValue}
import org.scalatest.Assertions

/**
  * Created by rudkodm on 4/13/16.
  */
trait JsonTestCommon extends Assertions {
  def $(result: JsLookupResult): JsValue = {
    result match {
      case JsDefined(v) => v
      case undefined: JsUndefined => fail(undefined.validationError.message)
    }
  }
}

object JsonTestCommon extends JsonTestCommon
