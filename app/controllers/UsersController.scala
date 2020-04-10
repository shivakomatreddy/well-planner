package controllers

import javax.inject._
import util.ResponseTypes._
import util.JsonFormats._
import com.google.inject.Inject
import model.api.users.{UserMessage, UsersFacade}
import model.dataModels.User
import play.api.db.DBApi
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.Future

@Singleton
class UsersController @Inject() (dbApi: DBApi, cc: ControllerComponents) extends AbstractController(cc) {

  private val api = new UsersFacade(dbApi)

  private def badRequest: Future[Result] =
    Future.successful(errorResponse(BAD_REQUEST, Seq("Unable to recognize request")))

  def login() = Action.async(BodyParsers.parse.json) { request =>
    def apiLogin(data: UserMessage) =
      api.login(data.username, data.password) match {
        case Some(user) =>
          Future.successful(successResponse(OK, Json.toJson(user), Seq(s"Successfully logged in user ${user.username}")))
        case None =>
          Future.successful(errorResponse(NOT_FOUND, Seq("Un authorized user. ", "Unable to find in system. ", "User already logged in. ")))
      }

    request.body.validate[UserMessage].fold(
      errors => badRequest,
      data => apiLogin(data)
    )
  }

  def logoff() = Action.async(BodyParsers.parse.json) { request =>
    def apiLogoff(data: UserMessage) =
      api.logOff(data.username, data.password) match {
        case Some(user) =>
          Future.successful(successResponse(OK, Json.toJson(user), Seq(s"Successfully logged off user ${user.username}")))
        case None =>
          Future.successful(errorResponse(NOT_FOUND, Seq("Un authorized user", "Unable to find in system", "Unable to log user off")))
      }

    request.body.validate[UserMessage].fold(
      errors => badRequest,
      data => apiLogoff(data)
    )
  }

  def register() = Action.async(BodyParsers.parse.json) { request =>
    def apiRegister(newUser: User) =
      api.register(newUser) match {
        case Some(newUser) => Future.successful(successResponse(CREATED, Json.toJson(newUser), Seq(s"Successfully registered $newUser")))
        case None => Future.successful(errorResponse(FOUND, Seq(s"${newUser.username} already exists")))
      }

    request.body.validate[User].fold(
      errors => badRequest,
      payload => apiRegister(payload)
    )
  }

  def usernameAndEmailExists(username: String, email: String) = Action.async {
    val userNameAndEmailExists = api.userNameAndEmailCheck(username, email)
    val jsonData = Json.toJson(s"{ usernameExists : ${userNameAndEmailExists.usernameExists}, emailExists: ${userNameAndEmailExists.emailExists}")
    val successMessage = Seq("true if it exists otherwise false if it doesn't exist")
    Future.successful(successResponse(OK, jsonData, successMessage))
  }

  def users() =  Action {
    successResponse(OK, Json.toJson(api.users()), Seq("Successfully processed"))
  }

  def delete() = Action.async(BodyParsers.parse.json) { request =>
    def apiRemove(data: UserMessage) =
      api.remove(data) match {
        case Some(msg) =>
          Future.successful(successResponseWithOptionalData(ACCEPTED, messages = Seq(msg)))
        case None =>
          Future.successful(errorResponse(INTERNAL_SERVER_ERROR, Seq(s"${data.username} unable to delete!", " unable to find user!")))
      }

    request.body.validate[UserMessage].fold(
      errors => badRequest,
      data => apiRemove(data)
    )
  }
}
