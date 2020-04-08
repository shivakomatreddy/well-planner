package model.api.businesses.db

import anorm.{Macro, RowParser, _}
import model.api.businesses.Business
import javax.inject.Inject
import model.api.PostgresDatabase
import play.api.db.DBApi

@javax.inject.Singleton
class BusinessesDb @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) with BusinessDbApi {

  val parser: RowParser[Business] = Macro.namedParser[Business]

  override def addNewBusiness(business: Business): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into businesses(name , city , state, country, modifiedDate, createdDate) " +
        "values ({name} , {city} , {state}, {country}, {modifiedDate}, {createdDate})")
        .on("name"  -> business.name, "city" -> business.city, "state" -> business.state,
                  "country" -> business.country, "modifiedDate" -> business.modifiedDate,
                  "createdDate" -> business.createdDate)
        .executeInsert()
    }
  }


  override def list(): Seq[Business] =
    db.withConnection { implicit connection =>
      SQL("select * from businesses").as(parser.*)
    }

}
