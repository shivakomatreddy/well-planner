package model.dataModels

import org.joda.time.DateTime

case class Business(id: Int, name: Option[String] = None, city: Option[String] = None, state: String,
                    country: Option[String] = None, modifiedDate: DateTime,
                    createdDate: DateTime)
