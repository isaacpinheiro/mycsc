package models

import java.util.Date
import play.api.libs.json.Json

case class Product(
  val id: Option[Long],
  val brand: String,
  val name: String,
  val description: Option[String],
  val img: Option[String],
  val createdAt: Date,
  val updatedAt: Date,
  val enterpriseUserId: Long
)

object Product {
  implicit val responseFormat = Json.format[Product]
}
