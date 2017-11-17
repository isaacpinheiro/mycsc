package models

import java.util.Date
import play.api.libs.json.Json

case class Message(
  val id: Option[Long],
  val messageType: String,
  val anonymous: Boolean,
  val content: String,
  val attachment: Option[String],
  val createdAt: Date,
  val updatedAt: Date,
  val commonUserId: Long,
  val enterpriseUserId: Option[Long],
  val productId: Option[Long]
)

object Message {
  implicit val responseFormat = Json.format[Message]
}
