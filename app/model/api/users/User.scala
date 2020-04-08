package model.api.users

case class User(username: String, password: String, loggedIn: Int = 0)