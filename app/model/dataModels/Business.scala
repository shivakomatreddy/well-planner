package model.dataModels

import org.joda.time.DateTime

case class Business(id: Option[Int] = None, name: String, city: String, state: String,
                    country: String, modifiedDate: DateTime = null, createdDate: DateTime= null)
