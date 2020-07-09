package model.databases

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.Project
import model.tools.AnormExtension._
import play.api.db.DBApi

@javax.inject.Singleton
class ProjectsDbFacade @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) with ProjectsDbAPI {

  val parser: RowParser[Project] = Macro.namedParser[Project]

  def addNewProject(project: Project): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into Projects(name , event_type , brides_name, notes, budget, grooms_name, business_id, modified_date, created_date) " +
        "values ({name} , {event_type} , {brides_name}, {notes}, {budget}, {grooms_name}, {business_id}, {modified_date}, {created_date})")
        .on("name"  -> project.name,
          "event_type" -> project.event_type,
          "brides_name" -> project.brides_name,
          "grooms_name" -> project.grooms_name,
          "notes" -> project.notes,
          "budget" -> project.budget,
          "business_id" -> project.business_id,
          "modified_date" -> project.modified_date,
          "created_date" -> project.created_date)
        .executeInsert()
    }
  }

  def updateProject(project: Project): Int = {
    db.withConnection { implicit connection =>
      SQL("update Projects(name , event_type , brides_name, notes, budget, grooms_name, business_id, modified_date, created_date) " +
        "values ({name} , {event_type} , {brides_name}, {notes}, {budget}, {grooms_name}, {business_id}, {modified_date}, {created_date})")
        .on("name"  -> project.name,
          "event_type" -> project.event_type,
          "brides_name" -> project.brides_name,
          "grooms_name" -> project.grooms_name,
          "notes" -> project.notes,
          "budget" -> project.budget,
          "business_id" -> project.business_id,
          "modified_date" -> project.modified_date,
          "created_date" -> project.created_date)
        .executeUpdate()
    }
  }

  def list(): Seq[Project] =
    db.withConnection { implicit connection =>
      SQL("select * from projects").as(parser.*)
    }

  def listByBusinessId(businessId:Long): Seq[Project] = ???

}
