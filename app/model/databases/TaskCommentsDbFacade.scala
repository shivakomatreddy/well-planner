package model.databases


import anorm.{Macro, RowParser, _}
import javax.inject.Inject
import model.dataModels.TaskComment
import play.api.db.DBApi
import model.tools.AnormExtension._


@javax.inject.Singleton
class TaskCommentsDbFacade @Inject() (dbApi: DBApi) extends PostgresDatabase(dbApi) {

  val parser: RowParser[TaskComment] = Macro.namedParser[TaskComment]

  def addNewTaskComment(taskComment: TaskComment): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL("insert into taskComments(commentText, taskId, userCreatedId, projectId, parentTaskId, businessId, modifiedDate, createdDate) " +
        "values ({commentText}, {taskId}, {userCreatedId}, {projectId}, {parentTaskId}, {businessId}, {modifiedDate}, {createdDate})")
        .on("commentText"  -> taskComment.commentText, "taskId" -> taskComment.taskId, "userCreatedId" -> taskComment.userCreatedId, "projectId" -> taskComment.projectId,
          "businessId" -> taskComment.businessId, "modifiedDate" -> taskComment.modifiedDate, "createdDate" -> taskComment.createdDate)
        .executeInsert()
    }
  }

  def list(): Seq[TaskComment] =
    db.withConnection { implicit connection =>
      SQL("select * from taskComments").as(parser.*)
    }

}
