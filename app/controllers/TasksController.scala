package controllers

import JsonFormat._
import model.TasksAPI
import play.api.Logger
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

class TasksController extends Controller {

  import Constants._

  def tasksByProject(projectId: Int) = Action.async {
    val tasksFuture = scala.concurrent.Future{
      TasksAPI.byProject(projectId, 1)
    }
    tasksFuture.map {
      tasks => Ok(Json.toJson(tasks))
    }
  }



}