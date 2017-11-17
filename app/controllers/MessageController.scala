package controllers

import java.util.Date

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DBApi

import anorm._
import anorm.{ Macro, RowParser }

import models.Message
import utils.MySQLDateFormat

@Singleton
class MessageController @Inject()(cc: ControllerComponents, dbapi: DBApi) extends AbstractController(cc) {

  val db = dbapi.database("default")
  val parser: RowParser[Message] = Macro.namedParser[Message]

  def listAll = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val result: List[Message] = SQL("select * from Message;").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def listOne(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val List(result): List[Message] = SQL("select * from Message where id = " + id + ";").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def create = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val m = Message(
        None,
        (obj \ "typeMessage").as[String],
        (obj \ "anonymous").as[Boolean],
        (obj \ "content").as[String],
        (obj \ "attachment").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "commonUserId").as[Long],
        (obj \ "enterpriseUserId").asOpt[Long],
        (obj \ "productId").asOpt[Long]
      )

      val attachment = m.attachment match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      val enterpriseUserId = m.enterpriseUserId match {
        case Some(x) => x
        case None => "null"
      }

      val productId = m.productId match {
        case Some(x) => x
        case None => "null"
      }

      SQL("insert into Message(typeMessage, anonymous, content, attachment, createdAt, updatedAt, commonUserId, enterpriseUserId, productId) " +
        "values('" +
        m.typeMessage + "', " +
        m.anonymous + "', '" +
        m.content + "', " +
        attachment + ", '" +
        MySQLDateFormat.format(m.createdAt) + "', '" +
        MySQLDateFormat.format(m.updatedAt) + "', " +
        m.commonUserId + ", " +
        enterpriseUserId + ", " +
        productId + ");").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def update = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val m = Message(
        Some((obj \ "id").as[Long]),
        (obj \ "typeMessage").as[String],
        (obj \ "anonymous").as[Boolean],
        (obj \ "content").as[String],
        (obj \ "attachment").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "commonUserId").as[Long],
        (obj \ "enterpriseUserId").asOpt[Long],
        (obj \ "productId").asOpt[Long]
      )

      val Some(id) = m.id

      val attachment = m.attachment match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      val enterpriseUserId = m.enterpriseUserId match {
        case Some(x) => x
        case None => "null"
      }

      val productId = m.productId match {
        case Some(x) => x
        case None => "null"
      }

      SQL("update Message set " +
        "typeMessage = '" + m.typeMessage + "', " +
        "anonymous = " + m.anonymous + ", " +
        "content = '" + m.content + "', " +
        "attachment = " + attachment + ", " +
        "createdAt = '" + MySQLDateFormat.format(m.createdAt) + "', " +
        "updatedAt = '" + MySQLDateFormat.format(m.updatedAt) + "', " +
        "commonUserId = " + m.commonUserId + ", " +
        "enterpriseUserId = " + enterpriseUserId + ", " +
        "productId" + productId + " " +
        "where id = " + id + ";").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def delete(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      SQL("delete from Message where id = " + id + ";").execute()
      Ok(Json.parse("{\"msg\": \"success\"}"))
    }
  }

}
