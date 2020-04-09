package controllers.util

import model.api.users.UserMessage
import model.dataModels.User
import play.api.libs.json.Json


object JsonFormats {

  implicit val userMessageFormat = Json.format[UserMessage]
  implicit val userFormat = Json.format[User]

}
