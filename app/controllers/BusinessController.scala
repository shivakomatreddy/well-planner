package controllers

import com.google.inject.Inject
import controllers.util.JsonFormats._
import controllers.util.ResponseTypes.{errorResponse, successResponse}
import model.api.businesses.{BusinessesApi, NewBusinessSignupMessage}
import play.api.Logger
import play.api.db.DBApi
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, BodyParsers, ControllerComponents, Result}

import scala.concurrent.Future

class BusinessController  @Inject() (dbApi: DBApi, cc: ControllerComponents) extends AbstractController(cc) {

  private val logger = Logger(this.getClass)

  private val businessesApi = new BusinessesApi(dbApi)

  private def badRequest: Future[Result] =
    Future.successful(errorResponse(BAD_REQUEST, Seq("Unable to recognize request")))

  def registerNewBusiness(): Action[JsValue] = Action.async(BodyParsers.parse.json) { request =>

    def apiRegister(newBusiness: NewBusinessSignupMessage): Future[Result] = {
      businessesApi.signUpBusiness(newBusiness) match {
        case Right(data) =>
          logger.info(s"Successfully registered \n user details follow : \n { ${Json.toJson(data).toString} } ")
          Future.successful(successResponse(CREATED, Json.toJson(data), Seq(s"Successfully registered ${data._1.name}")))
        case Left(failure) => Future.successful(errorResponse(FOUND, Seq(s"Error: $failure")))
      }
    }

    request.body.validate[NewBusinessSignupMessage].fold(
      errors => badRequest,
      payload => apiRegister(payload)
    )
  }

}
