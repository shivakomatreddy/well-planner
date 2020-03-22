package controllers

import com.google.inject.Inject
import model.{ApplicationAPI, Business, BusinessRepo}
import play.api.Logger
import play.api.libs.json.Json._
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc.{Action, BodyParsers, Controller}
import utils.Constants._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class BusinessController  @Inject() (businessRepository: BusinessRepo) extends Controller {

  val logger = Logger(this.getClass())


  def registerNewBusiness() = Action.async(BodyParsers.parse.json) { request =>
    request.body.validate[Business].fold(
      errors => {
        logger.error("UserController.register() - ERROR LOG - " + JsError.toJson(errors))
        Future.successful(BadRequest(Json.obj("status" -> ERROR, "message" -> JsError.toJson(errors))))
      },
      newBusiness => {
        ApplicationAPI.createBusiness(newBusiness, businessRepository).map {
          newBusinessID => Ok(Json.obj("status" -> SUCCESS, "message" -> ("New business with id, '" + newBusinessID + "' registered successfully.")))
        }
      }
    )
  }
}
