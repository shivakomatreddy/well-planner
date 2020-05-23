package controllers

import com.google.inject.Inject
import controllers.util.JsonFormats._
import controllers.util.ResponseTypes._
import model.api.businesses.{AdminSignUpMessage, BusinessesApi, NewBusinessSignupMessage}
import play.api.Logger
import play.api.db.DBApi
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class BusinessController  @Inject() (dbApi: DBApi, cc: ControllerComponents, ws: WSClient) extends AbstractController(cc) {

  private val logger = Logger(this.getClass)

  private val businessesApi = new BusinessesApi(dbApi, ws)

  private def badRequest: Future[Result] =
    Future.successful(errorResponse(BAD_REQUEST, Seq("Unable to recognize request")))

  def registerNewBusiness(): Action[JsValue] = Action.async(BodyParsers.parse.json) { request =>

    def apiRegister(newBusiness: AdminSignUpMessage): Future[Result] = {
      businessesApi.signUpBusiness(newBusiness) match {
        case Right(data) =>
          logger.info(s"Successfully registered \n user details follow : \n { ${Json.toJson(data).toString} } ")
          Future.successful(successResponse(CREATED, Json.toJson(data), Seq(s"Successfully registered ${data._1.name}")))
        case Left(failure) => Future.successful(errorResponse(FOUND, Seq(s"Error: $failure")))
      }
    }

    request.body.validate[AdminSignUpMessage].fold(
      errors => badRequest,
      payload => {
        println(payload.toString)
        apiRegister(payload)
      }
    )
  }

  def businessExists(businessName: String) = Action.async {
    val businessExists = businessesApi.businessExists(businessName)
    val successMessage = Seq("true if it exists otherwise false if it doesn't exist")
    Future.successful(successBooleanResponse(OK, businessExists, successMessage))
  }

}