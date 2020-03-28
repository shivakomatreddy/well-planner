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

  def projectTasks = Action {
    Ok(views.html.projectTasks("Project Tasks"))
  }

  def projectBudgetPage = Action {
    Ok(views.html.budget("Project Budget"))
  }

  def projectsPages = Action {
    Ok(views.html.projects("Projects"))
  }

  def mainDashboard = Action {
    Ok(views.html.mainDashboard("Main Dashboard"))
  }

  def settingsPage = Action {
    Ok(views.html.projectSettings("Company Settings"))
  }

  def loginPage = Action {
    Ok(views.html.login())
  }

  def registerPage = Action {
    Ok(views.html.register())
  }

  def customerPage = Action {
    Ok(views.html.customers("Customers"))
  }

  def invoicesPage = Action {
    Ok(views.html.invoices("Invoices"))
  }

  def businessUserRegisterPage = Action {
    Ok(views.html.businessUserRegistration("Welcome to the registration process"))
  }

}
