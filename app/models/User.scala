package models

import java.util.Date
import play.api.libs.json.Json

case class User(
  val id: Option[Long],
  val email: String,
  val password: String,
  val token: Int,
  val createdAt: Date,
  val updatedAt: Date
)

object User {
  implicit val responseFormat = Json.format[User]
}
