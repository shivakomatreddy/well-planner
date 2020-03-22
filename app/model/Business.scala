package model

import play.api.libs.json._

case class Business(id: Long, name: String, city: String, state: String, country: String)

object Business {
  implicit val businessFormat = Json.format[Business]
}
