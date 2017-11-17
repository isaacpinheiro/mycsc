package controllers

import java.util.Date

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DBApi

import anorm._
import anorm.{ Macro, RowParser }

import models.Product
import utils.MySQLDateFormat

@Singleton
class ProductController @Inject()(cc: ControllerComponents, dbapi: DBApi) extends AbstractController(cc) {

  val db = dbapi.database("default")
  val parser: RowParser[Product] = Macro.namedParser[Product]

  def listAll = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val result: List[Product] = SQL("select * from Product;").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def listOne(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val List(result): List[Product] = SQL("select * from Product where id = " + id + ";").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def create = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val p = Product(
        None,
        (obj \ "brand").as[String],
        (obj \ "name").as[String],
        (obj \ "description").asOpt[String],
        (obj \ "img").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "enterpriseUserId").as[Long]
      )

      val description = p.description match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      val img = p.img match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      SQL("insert into Product(brand, name, description, img, createdAt, updatedAt, enterpriseUserId) " +
        "values('" +
        p.brand + "', '" +
        p.name + "', " +
        description + ", " +
        img + ", '" +
        MySQLDateFormat.format(p.createdAt) + "', '" +
        MySQLDateFormat.format(p.updatedAt) + "', " +
        p.enterpriseUserId + ");").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def update = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val p = Product(
        Some((obj \ "id").as[Long]),
        (obj \ "brand").as[String],
        (obj \ "name").as[String],
        (obj \ "description").asOpt[String],
        (obj \ "img").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "enterpriseUserId").as[Long]
      )

      val Some(id) = p.id

      val description = p.description match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      val img = p.img match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      SQL("update Product set " +
        "brand = '" + p.brand + "', " +
        "name = '" + p.name + "', " +
        "description = " + description + ", " +
        "img = " + img + ", " +
        "createdAt = '" + MySQLDateFormat.format(p.createdAt) + "', " +
        "updatedAt = '" + MySQLDateFormat.format(p.updatedAt) + "', " +
        "enterpriseUserId = " + p.enterpriseUserId + " " +
        "where id = " + id + ";").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def delete(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      SQL("delete from Product where id = " + id + ";").execute()
      Ok(Json.parse("{\"msg\": \"success\"}"))
    }
  }

}
