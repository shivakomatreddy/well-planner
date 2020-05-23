package model.dataModels

import org.joda.time.DateTime

case class User(id: Option[Int] = None, userAuth0Id: String, username: String, password: String, loggedIn: Int = 0,
                email: String, businessId: Int = 0, isAdmin: Boolean = false,
                isCustomer: Boolean = false, isAnEmployee: Boolean = false,
                modifiedDate: DateTime = null, createdDate: DateTime= null)