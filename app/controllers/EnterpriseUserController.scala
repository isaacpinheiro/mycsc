package controllers

import java.util.Date

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.db.DBApi

import anorm._
import anorm.{ Macro, RowParser }

import models.EnterpriseUser
import utils.MySQLDateFormat

@Singleton
class EnterpriseUserController @Inject()(cc: ControllerComponents, dbapi: DBApi) extends AbstractController(cc) {

  val db = dbapi.database("default")
  val parser: RowParser[EnterpriseUser] = Macro.namedParser[EnterpriseUser]

  def listAll = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val result: List[EnterpriseUser] = SQL("select * from EnterpriseUser;").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def listOne(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      val List(result): List[EnterpriseUser] = SQL("select * from EnterpriseUser where id = " + id + ";").as(parser.*)
      Ok(Json.toJson(result))
    }
  }

  def create = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val e = EnterpriseUser(
        None,
        (obj \ "tradeName").as[String],
        (obj \ "regCode").as[String],
        (obj \ "img").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "userId").as[Long]
      )

      val img = e.img match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      SQL("insert into EnterpriseUser(tradeName, regCode, img, createdAt, updatedAt, userId) " +
        "values('" +
        e.tradeName + "', '" +
        e.regCode + "', " +
        img + ", '" +
        MySQLDateFormat.format(e.createdAt) + "', '" +
        MySQLDateFormat.format(e.updatedAt) + "', " +
        e.userId + ");").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def update = Action(parse.json) { implicit request: Request[JsValue] =>

    db.withConnection { implicit conn =>

      val obj = request.body
      val e = EnterpriseUser(
        Some((obj \ "id").as[Long]),
        (obj \ "tradeName").as[String],
        (obj \ "regCode").as[String],
        (obj \ "img").asOpt[String],
        (obj \ "createdAt").as[Date],
        (obj \ "updatedAt").as[Date],
        (obj \ "userId").as[Long]
      )

      val Some(id) = e.id

      val img = e.img match {
        case Some(x) => "'" + x + "'"
        case None => "null"
      }

      SQL("update EnterpriseUser set " +
        "tradeName = '" + e.tradeName + "', " +
        "regCode = '" + e.regCode + "', " +
        "img = " + img + ", " +
        "createdAt = '" + MySQLDateFormat.format(e.createdAt) + "', " +
        "updatedAt = '" + MySQLDateFormat.format(e.updatedAt) + "', " +
        "userId = " + e.userId + " " +
        "where id = " + id + ";").execute()

      Ok(Json.parse("{\"msg\": \"success\"}"))

    }

  }

  def delete(id: Long) = Action { implicit request: Request[AnyContent] =>
    db.withConnection { implicit conn =>
      SQL("delete from EnterpriseUser where id = " + id + ";").execute()
      Ok(Json.parse("{\"msg\": \"success\"}"))
    }
  }

}
