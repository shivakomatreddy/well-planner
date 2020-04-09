package model.databases

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.Client
import model.tools.AnormExtension._
import play.api.db.DBApi

@javax.inject.Singleton
class ClientsDB @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi)  {

  val parser: RowParser[Client] = Macro.namedParser[Client]

  def addNewClient(client: Client): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into clients(name , eventType , email, notes, budget, status, businessId, modifiedDate, createdDate) " +
        "values ({name} , {city} , {state}, {country}, {modifiedDate}, {createdDate})")
        .on("name"  -> client.name, "eventType" -> client.eventType, "email" -> client.email,
          "notes" -> client.notes, "budget" -> client.budget, "status" -> client.status,
          "modifiedDate" -> client.modifiedDate, "createdDate" -> client.createdDate)
        .executeInsert()
    }
  }


  def list(): Seq[Client] =
    db.withConnection { implicit connection =>
      SQL("select * from clients").as(parser.*)
    }

}

