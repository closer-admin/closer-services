package com.closer.regions.data.entities

case class Region(
                   id: String,
                   name: String,
                   description: String,
                   zone: Zone,
                   promotions: Set[Promotion]
                 )
