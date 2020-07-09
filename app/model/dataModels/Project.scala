package model.dataModels

import org.joda.time.DateTime

case class Project(id: Option[Int] = None,
                   name: String,
                   event_type: Option[String] = None,
                   brides_name: Option[String] = None,
                   grooms_name: Option[String] = None,
                   due_date: String,
                   notes: Option[String] = None,
                   budget: Double,
                   business_id: Int,
                   modified_date: Int,
                   created_date: Option[Int] = None)