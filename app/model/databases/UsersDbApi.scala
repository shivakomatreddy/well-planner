package model.databases

import model.dataModels.User

trait UsersDbApi  {

  def login(username: String, password: String): Option[User]

  def logOff(username: String, password: String): Option[User]

  def find(username: String, password: String): Option[User]

  def add(user: User): Option[User]

  def list(): Seq[User]

  def delete(username: String, password: String): Option[String]

}
