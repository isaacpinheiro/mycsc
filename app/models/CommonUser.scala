package models

import java.util.Date
import play.api.libs.json.Json

case class CommonUser(
  val id: Option[Long],
  val name: String,
  val img: Option[String],
  val createdAt: Date,
  val updatedAt: Date,
  val userId: Long
)

object CommonUser {
  implicit val responseFormat = Json.format[CommonUser]
}
