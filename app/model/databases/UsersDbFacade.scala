package model.databases

import java.sql.Connection

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.User
import play.api.db.DBApi
import model.tools.AnormExtension._

@javax.inject.Singleton
class UsersDbFacade @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) with UsersDbApi {

  val parser: RowParser[User] = Macro.namedParser[User]

  override def login(username: String, password: String): Option[User] =
    loginState(username, password, 1)

  override def logOff(username: String, password: String): Option[User] =
    loginState(username, password, 0)

  private def loginState(username: String, password: String, status: Int): Option[User] =
    db.withConnection { implicit connection =>
      SQL("update users set loggedIn = {status} where username = {username} and password = {password}")
          .on("username" -> username, "password" -> password, "status" -> status)
          .executeUpdate()
      find(username, password)
    }

  override def find(username: String, password: String): Option[User] =
    db.withConnection { implicit connection =>
      SQL("select * from users where username = {username} and password = {password}")
        .on("username" -> username, "password" -> password)
        .as(parser.singleOpt)
    }

  override def userNameExists(username: String): Boolean = {
    db.withConnection { implicit connection =>
      findByUsername(username).nonEmpty
    }
  }

  override def emailExists(email: String): Boolean = {
    db.withConnection { implicit connection =>
      SQL("select * from users where email = {email}")
        .on("email" -> email)
        .as(parser.singleOpt)
        .nonEmpty
    }
  }

  override def list(): Seq[User] =
    db.withConnection { implicit connection =>
      SQL("select * from users").as(parser.*)
    }

  override def add(u: User): Option[User] =
    db.withConnection { implicit connection =>
      if(findByUsername(u.username).isEmpty) {
        SQL("insert into users(username, password, loggedIn, email, businessId, isAdmin, isCustomer, " +
                   "isAnEmployee, modifiedDate, createdDate) " +
           "values ({username}, {password}, {loggedIn}, {email}, {businessId}, " +
                   "{isAdmin}, {isCustomer}, {isAnEmployee}, {modifiedDate}, {createdDate})")
          .on("username" -> u.username, "password" -> u.password, "loggedIn" -> u.loggedIn, "email" -> u.email,
                    "businessId" -> u.businessId + "isAdmin" -> u.isAdmin, "isCustomer" -> u.isCustomer,
                    "isAnEmployee" -> u.isAnEmployee + "modifiedDate" -> u.modifiedDate + "createdDate" -> u.createdDate)
          .executeInsert()
        find(u.username, u.password)
      }
      else None
    }

  override def delete(username: String, password: String): Option[String] =
    db.withConnection { implicit connection =>
      val numberOfRowsUpdated =
        SQL("delete from users where username = {username} and password = {password}")
          .on("username" -> username, "password" -> password).executeUpdate()
      if(numberOfRowsUpdated > 0)
        Some(s"Successfully deleted user with username -> $username")
      else None
    }

  private def findByUsername(username: String)(implicit connection: Connection): Option[User] =
    SQL("select * from users where username = {username}").on("username" -> username).as(parser.singleOpt)

}

