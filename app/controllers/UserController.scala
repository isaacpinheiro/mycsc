package controllers

import java.util.Date

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DBApi

import anorm._
import anorm.{ Macro, RowParser }

import models.User
import utils.MySQLDateFormat

@Singleton
class UserController @Inject()(cc: ControllerComponents, dbapi: DBApi) extends AbstractController(cc) {

  val db = dbapi.database("default")
  val parser: RowParser[User] = Macro.namedParser[User]

  def listAll = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val result: List[User] = SQL("select * from User;").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def listOne(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val List(result): List[User] = SQL("select * from User where id = " + id + ";").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def create = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val u = User(
        None,
        (obj \ "email").as[String],
        (obj \ "password").as[String],
        (obj \ "token").as[Int],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date]
      )

      SQL("insert into User(email, password, token, createdAt, updatedAt) " +
        "values('" +
        u.email + "', '" +
        u.password + "', " +
        u.token + ", '" +
        MySQLDateFormat.format(u.createdAt) + "', '" +
        MySQLDateFormat.format(u.updatedAt) + "');").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def update = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val u = User(
        Some((obj \ "id").as[Long]),
        (obj \ "email").as[String],
        (obj \ "password").as[String],
        (obj \ "token").as[Int],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date]
      )

      val Some(id) = u.id

      SQL("update User set " +
        "email = '" + u.email + "', " +
        "password = '" + u.password + "', " +
        "token = " + u.token + ", " +
        "createdAt = '" + MySQLDateFormat.format(u.createdAt) + "', " +
        "updatedAt = '" + MySQLDateFormat.format(u.updatedAt) + "' " +
        "where id = " + id + ";").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def delete(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      SQL("delete from User where id = " + id + ";").execute()
      Ok(Json.parse("{\"msg\": \"success\"}"))
    }
  }

}
