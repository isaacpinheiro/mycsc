package models

import java.util.Date
import play.api.libs.json.Json

case class Chat(
  val id: Option[Long],
  val content: String,
  val sentOn: Date,
  val fromUserId: Long,
  val toUserId: Long
)

object Chat {
  implicit val responseFormat = Json.format[Chat]
}
