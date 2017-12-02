package controllers

import javax.inject.Inject

import com.google.inject.Provider
import play.api.Application
import play.api.i18n.{LangImplicits, Messages, MessagesApi}
import play.api.mvc._

class AppPage @Inject()(messagesApi: MessagesApi, appProvider: Provider[Application], implicit val assets: AssetsFinder) extends InjectedController with LangImplicits {
  implicit def message(implicit request: RequestHeader): Messages  = messagesApi.preferred(request)
  implicit def app: Application = appProvider.get()
  def index = Action { implicit request =>
    Ok(views.html.Application.index(request.flash))
  }

}
