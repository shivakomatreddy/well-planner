package model.dataModels

import org.joda.time.DateTime

case class Client(id: Int, name: Option[String] = None, eventType: Option[String] = None,
                  email: Option[String] = None, notes: Option[String] = None, budget: Option[Double] = None,
                  status: Option[String] = None, businessId: Int, modifiedDate: DateTime,
                  createdDate: DateTime)
