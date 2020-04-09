package model.dataModels

import org.joda.time.DateTime

case class Project(id: Int, name: Option[String] = None, eventType: Option[String] = None,
                       bridesName: Option[String] = None, groomsName: Option[String] = None,
                       notes: Option[String] = None, budget: Option[Double] = None, businessId: Int,
                       clientId: Int, modifiedDate: DateTime, createdDate: DateTime)