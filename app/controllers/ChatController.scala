package controllers

import java.util.Date

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DBApi

import anorm._
import anorm.{ Macro, RowParser }

import models.Chat
import utils.MySQLDateFormat

@Singleton
class ChatController @Inject()(cc: ControllerComponents, dbapi: DBApi) extends AbstractController(cc) {

  val db = dbapi.database("default")
  val parser: RowParser[Chat] = Macro.namedParser[Chat]

  def listAll = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val result: List[Chat] = SQL("select * from Chat;").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def listOne(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val List(result): List[Chat] = SQL("select * from Chat where id = " + id + ";").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def create = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val c = Chat(
        None,
        (obj \ "content").as[String],
        (obj \ "sentOn").as[Date],
        (obj \ "fromUserId").as[Long],
        (obj \ "toUserId").as[Long]
      )

      SQL("insert into Chat(content, sentOn, fromUserId, toUserId) " +
        "values('" +
        c.content + "', '" +
        MySQLDateFormat.format(c.sentOn) + "', " +
        c.fromUserId + ", " +
        c.toUserId + ");").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def update = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val c = Chat(
        Some((obj \ "id").as[Long]),
        (obj \ "content").as[String],
        (obj \ "sentOn").as[Date],
        (obj \ "fromUserId").as[Long],
        (obj \ "toUserId").as[Long]
      )

      val Some(id) = c.id

      SQL("update Chat set " +
        "content = '" + c.content + "', " +
        "sentOn = '" + MySQLDateFormat.format(c.sentOn) + "', " +
        "fromUserId = " + c.fromUserId + ", " +
        "toUserId = " + c.toUserId + " " +
        "where id = " + id + ";").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def delete(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      SQL("delete from Chat where id = " + id + ";").execute()
      Ok(Json.parse("{\"msg\": \"success\"}"))
    }
  }

}
