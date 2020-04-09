package model.api.users

import javax.inject.Inject
import model.dataModels.{Business, User}
import model.databases.{UsersDbApi, UsersDbFacade}
import play.api.db.DBApi

@javax.inject.Singleton
class UsersFacade @Inject() (dbApi: DBApi) extends UsersApi {

  private val db: UsersDbApi = new UsersDbFacade(dbApi)

  override def login(username: String, password: String): Option[User] =
    for {
      user <- db find(username, password)
      loggedInUser <- if(user.loggedIn == 0) db login(user.username, user.password) else None
    } yield loggedInUser

  override def logOff(username: String, password: String): Option[User] =
    for {
      user <- db find(username, password)
      loggedOffUser <- db logOff(user.username, user.password)
    } yield loggedOffUser

  override def register(newUser: User): Option[User] = {
    db add newUser
  }

  override def users(): Seq[User] =
    db list

  override def remove(userMessage: UserMessage): Option[String] =
    db delete(userMessage.username, userMessage.password)

}
