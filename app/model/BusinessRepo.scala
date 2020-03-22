package model

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class BusinessRepo @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class BusinessesTable(tag: Tag) extends Table[Business](tag, "businesses") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def city = column[String]("city")
    def state = column[String]("state")
    def country = column[String]("country")
    def * = (id, name, city, state, country) <> ((Business.apply _).tupled, Business.unapply)
  }

  private val businesses = TableQuery[BusinessesTable]

  def create(name: String, city: String, state: String, country: String): Future[Business] = db.run {
    (businesses.map(b => (b.name, b.city, b.state, b.country))
      returning businesses.map(_.id)
      into ((businessDetails, id) => Business(id, businessDetails._1, businessDetails._2, businessDetails._3, businessDetails._4))
    ) += (name, city, state, country)
  }

  def list(): Future[Seq[Business]] = db.run {
    businesses.result
  }
}