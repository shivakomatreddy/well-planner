package model.dataModels

import org.joda.time.DateTime

case class Business(id: Int, name: String, city: String, state: String,
                    country: String, modifiedDate: DateTime,
                    createdDate: DateTime)
