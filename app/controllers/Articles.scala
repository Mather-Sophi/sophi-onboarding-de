package controllers

import javax.inject.Inject
import models.ArticleReader
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, Controller, ControllerComponents}

class Articles  @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * RESTAPI
    * Create an Action to respond the http request to getArticles
    * and send back the Json object for sorted articles
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/api/articles/getarticles`.
    */
  def getArticles = {
    Action { implicit request => {
//      val csvFilePath = routes.Assets.versioned("resources/sample-input.csv").path()
      val csvFilePath = "./public/resources/sample-input.csv"
      val json = ArticleReader.getArticlesJson(csvFilePath)
      Ok(json)
    }
    }
  }
}
