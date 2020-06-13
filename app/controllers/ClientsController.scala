package controllers

import com.google.inject.Inject
import controllers.util.JsonFormats._
import controllers.util.ResponseTypes.{errorResponse, successResponse}
import model.api.clients.{ClientsApi, NewClientMessage}
import play.api.Logger
import play.api.db.DBApi
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.Future

class ClientsController  @Inject() (dbApi: DBApi, cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) {

  private val logger = Logger(this.getClass)

  private val clientsApi = new ClientsApi(dbApi, ws)

  def logForSuccess(data: String) =
    logger.info(s"Successfully created a new client and details follow : \n { $data } ")

  private def badRequest: Future[Result] =
    Future.successful(errorResponse(BAD_REQUEST, Seq("Unable to recognize request")))

  def newClient(): Action[JsValue] = Action.async(BodyParsers.parse.json) { request =>
    println("Register new client request accepted ")

    def createClient(newClient: NewClientMessage): Future[Result] =
      clientsApi.addNewClient(newClient) match {
        case Right(data) =>
          logForSuccess(Json.toJson(data).toString)
          Future.successful(successResponse(CREATED, Json.toJson(data), Seq(s"Successfully registered ${data.name}")))
        case Left(errorMsg) =>
          Future.successful(errorResponse(FOUND, Seq(s"Error: $errorMsg")))
      }

    request.body.validate[NewClientMessage].fold(
      errors => badRequest,
      payload => createClient(payload)
    )

  }

}

