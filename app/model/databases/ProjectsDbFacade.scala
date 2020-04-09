package model.databases

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.Project
import model.tools.AnormExtension._
import play.api.db.DBApi

@javax.inject.Singleton
class ProjectsDbFacade @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) {

  val parser: RowParser[Project] = Macro.namedParser[Project]

  def addNewProject(project: Project): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into Projects(name , eventType , bridesName, notes, budget, groomsName, businessId, customerId, modifiedDate, createdDate) " +
        "values ({name} , {city} , {state}, {country}, {modifiedDate}, {createdDate})")
        .on("name"  -> project.name, "eventType" -> project.eventType, "bridesName" -> project.bridesName,
          "groomsName" -> project.groomsName, "notes" -> project.notes, "budget" -> project.budget,
          "business_id" -> project.businessId, "clientId" -> project.clientId,
          "modifiedDate" -> project.modifiedDate, "createdDate" -> project.createdDate)
        .executeInsert()
    }
  }

  def list(): Seq[Project] =
    db.withConnection { implicit connection =>
      SQL("select * from projects").as(parser.*)
    }

}
