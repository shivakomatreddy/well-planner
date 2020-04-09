package model.dataModels

import org.joda.time.DateTime

case class User(id: Int, username: String, password: String, loggedIn: Int = 0,
                email: Option[String] = None, businessId: Int, isAdmin: Boolean,
                isCustomer: Boolean, isAnEmployee: Boolean,
                modifiedDate: DateTime, createdDate: DateTime)