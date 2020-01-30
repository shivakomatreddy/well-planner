package controllers

import javax.inject.Inject
import play.api.cache._
import play.api.libs.json._
import play.api.mvc._

class ProfileController @Inject() (cache: DefaultSyncCacheApi) extends Controller {

  def AuthenticatedAction(f: Request[AnyContent] => Result): Action[AnyContent] = {
    Action {
      request =>
        request.session.get("id")
          .flatMap(id => Some(cache.get[JsValue](id + "profile")))
          .map(profile => f(request))
          .orElse(Some(Redirect(routes.HomeController.index()))).get
    }
  }

  def getUserId(session: Session): Int = {
    val id = session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    (profile \ "app_user_id").as[Int]
  }

  def profilePage: Action[AnyContent] = AuthenticatedAction { request =>
    val id = request.session.get("id").get
    val profile = cache.get[JsValue](id + "profile").get
    val userId = (profile \ "app_user_id").as[Int]
    Ok(views.html.projectTasks("profile page (needs to be deleted)"))
  }

}

