package model.api.users

trait UsersApi {

  def login(username: String, password: String): Option[User]

  def logOff(username: String, password: String): Option[User]

  def register(username: String, password: String): Option[User]

  def users(): Seq[User]

  def remove(userMessage: UserMessage): Option[String]

}
