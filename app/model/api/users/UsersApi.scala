package model.api.users

import model.dataModels.User

trait UsersApi {

  def login(username: String, password: String): Option[User]

  def logOff(username: String, password: String): Option[User]

  def register(newUser: User): Option[User]

  def users(): Seq[User]

  def remove(userMessage: UserMessage): Option[String]

}
