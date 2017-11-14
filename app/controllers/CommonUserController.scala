package controllers

import java.util.Date

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DBApi

import anorm._
import anorm.{ Macro, RowParser }

import models.CommonUser
import utils.MySQLDateFormat

@Singleton
class CommonUserController @Inject()(cc: ControllerComponents, dbapi: DBApi) extends AbstractController(cc) {

  val db = dbapi.database("default")
  val parser: RowParser[CommonUser] = Macro.namedParser[CommonUser]

  def listAll = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit c =>
      val result: List[CommonUser] = SQL("select * from CommonUser;").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def listOne(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit c =>
      val List(result): List[CommonUser] = SQL("select * from CommonUser where id = " + id + ";").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def create = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit c =>

      val obj = request.body
      val c = CommonUser(
        None,
        (obj \ "name").as[String],
        (obj \ "img").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "userId").as[Long]
      )

      val img = c.img match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      /*SQL("insert into CommonUser(name, img, createdAt, updatedAt, userId) " +
        "values('" +
        c.name + "', " +
        img + ", '" +
        MySQLDateFormat.format(c.createdAt) + "', '" +
        MySQLDateFormat.format(c.updatedAt) + "', " +
        c.userId + ");").execute()*/

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def update = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit c =>

      val obj = request.body
      val c = CommonUser(
        Some((obj \ "id").as[Long]),
        (obj \ "name").as[String],
        (obj \ "img").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "userId").as[Long]
      )

      val Some(id) = c.id

      val img = c.img match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      /*SQL("update CommonUser set " +
        "name = '" + c.name + "', " +
        "img = " + img + ", " +
        "createdAt = '" + MySQLDateFormat.format(c.createdAt) + "', " +
        "updatedAt = '" + MySQLDateFormat.format(c.updatedAt) + "', " +
        "userId = " + c.userId + " " +
        "where id = " + id + ";").execute()*/

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def delete(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit c =>
      SQL("delete from CommonUser where id = " + id + ";").execute()
      Ok(Json.parse("{\"msg\": \"success\"}"))
    }
  }

}
