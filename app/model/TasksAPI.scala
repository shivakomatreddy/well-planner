package model

object TasksAPI {

  var currentId = 0

  def byProject(projectId: Int, companyId: Int): Seq[Task] = {
    Database.tasks.filter(item => (item.projectId == projectId && item.companyId == companyId))
  }

  def addItem(checkListItem: Task): Unit = {
    Database.tasks = Database.tasks ++ Seq(checkListItem)
  }

  def completeTask(taskId: Int): Option[Task] = {
    val task = Database.tasks.find(_.id.get == taskId)
    task.map(tsk => {
      updateTask(tsk.copy(isCompleted = true))
    })
  }

  def updateTask(task: Task): Task = {
    Database.tasks = Database.tasks.filter(_.id != task.id)
    Database.tasks = Database.tasks ++ Seq(task)
    task
  }

  def deleteTask(taskId: Int, companyId: Int): Unit =
    Database.tasks = Database.tasks.filter(t => t.id.get != taskId && t.companyId == companyId)
}
