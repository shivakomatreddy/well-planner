package controllers

import model.User
import play.api.libs.json.Json


object JsonFormat {
  implicit val user = Json.format[User]
}
