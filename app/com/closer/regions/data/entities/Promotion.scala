package com.closer.regions.data.entities

import org.joda.time.DateTime

/**
  * Created by rudkodm on 4/17/16.
  */
case class Promotion(
                      id: String,
                      serviceId: String,
                      promoCode: String,
                      media: String,
                      title: String,
                      expirationDateTime: DateTime,
                      shortDescription: String,
                      fullDescription: String,
                      rule: Rule
                    )
