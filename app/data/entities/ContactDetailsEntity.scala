package data.entities

case class ContactDetailsEntity(
                                 phoneNumber1: Option[String] = None,
                                 phoneNumber2: Option[String] = None,
                                 emailAddress: Option[String] = None,
                                 webSite: Option[String] = None
                               )
