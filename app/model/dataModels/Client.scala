package model.dataModels

import org.joda.time.DateTime

case class Client(id: Option[Int] = None, name: Option[String] = None, event_type: Option[String] = None,
                  email: Option[String] = None, notes: Option[String] = None, budget: Option[Double] = None,
                  status: Option[String] = None, business_id: Int, modified_date: Int,
                  created_date: Option[Int] = None)
