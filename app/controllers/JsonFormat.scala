package controllers

import model.{Task, User}
import play.api.libs.json.Json


object JsonFormat {
  implicit val user = Json.format[User]
  implicit val task = Json.format[Task]
}
