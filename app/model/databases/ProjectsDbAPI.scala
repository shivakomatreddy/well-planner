package model.databases

import model.dataModels.Project

trait ProjectsDbAPI {

  def addNewProject(project: Project): Option[Long]

  def updateProject(project: Project): Int

  def list(): Seq[Project]

  def listByBusinessId(businessId:Long): Seq[Project]

}
