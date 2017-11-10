package models

import java.util.Date
import play.api.libs.json.Json

case class EnterpriseUser(
  val id: Option[Long],
  val tradeName: String,
  val regCode: String,
  val img: Option[String],
  val createdAt: Date,
  val updatedAt: Date,
  val userId: Long
)

object EnterpriseUser {
  implicit val responseFormat = Json.format[EnterpriseUser]
}
