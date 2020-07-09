package model.dataModels

import org.joda.time.DateTime

case class User(id: Option[Int] = None, user_auth_0_id: String, username: String, password: String, logged_in: Boolean = false,
                email: String, business_id: Int = 1, is_admin: Boolean = false,
                is_customer: Boolean = false, is_an_employee: Boolean = false,
                modified_date: Int, created_date: Int)