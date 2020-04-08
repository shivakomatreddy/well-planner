package controllers.util

import model.api.users.{User, UserMessage}
import play.api.libs.json.Json


object JsonFormats {

  implicit val userMessageFormat = Json.format[UserMessage]
  implicit val userFormat = Json.format[User]

}
