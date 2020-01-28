package controllers

import javax.inject._
import play.api.mvc._
import javax.inject.Inject
import play.api.libs.json.JsValue

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def welcome = Action {
    Ok(views.html.welcome_page("Welcome to Travel Buddy - Travel management site for everyone"))
  }

  def profile = Action {
    Ok(views.html.profile("Your new application is ready."))
  }
}
