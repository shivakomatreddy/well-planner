package model.dataModels

import org.joda.time.DateTime

case class User(id: Option[Int] = None, username: String, password: String, loggedIn: Int = 0,
                email: Option[String] = None, businessId: Option[Int], isAdmin: Boolean = false,
                isCustomer: Boolean = false, isAnEmployee: Boolean = false,
                modifiedDate: DateTime = null, createdDate: DateTime= null)