package model.databases

import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.Task
import play.api.db.DBApi
import model.tools.AnormExtension._

@javax.inject.Singleton
class TasksDbFacade @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) {

  val parser: RowParser[Task] = Macro.namedParser[Task]

  def addNewTask(task: Task): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into tasks(title , description , notes, isCategory, isVisibleToCustomer," +
          " dueDate, assignedUserId, userCreatedId, projectId, parentTaskId, businessId, modifiedDate, createdDate) " +
          "values ({name} , {city} , {state}, {country}, {modifiedDate}, {createdDate})")
      .on("title"  -> task.title, "description" -> task.description, "notes" -> task.notes,
            "isCategory" -> task.isCategory, "isVisibleToCustomer" -> task.isVisibleToCustomer, "dueDate" -> task.dueDate,
            "assignedUserId" -> task.assignedUserId, "userCreatedId" -> task.userCreatedId, "projectId" -> task.projectId,
            "parentTaskId" -> task.parentTaskId, "businessId" -> task.businessId,
            "modifiedDate" -> task.modifiedDate, "createdDate" -> task.createdDate)
        .executeInsert()
    }
  }

  def list(): Seq[Task] =
    db.withConnection { implicit connection =>
      SQL("select * from tasks").as(parser.*)
    }

}

