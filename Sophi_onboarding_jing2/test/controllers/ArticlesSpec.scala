package controllers

import play.api.libs.json.Json
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}


class ArticlesSpec extends PlaySpecification  {
  "Articles controller" should {
    "getArticles" in new WithApplication() {
      route(app, FakeRequest(controllers.routes.Articles.getArticles())) match {
        case Some(response) => status(response) must equalTo(OK)
          contentAsJson(response) must equalTo(
            Json.parse(
              """
                |{"Articles":
                |[{"Article Name":"Federal government to consult public on banning handguns",
                |  "Date Submitted":"1900-01-01T00:00:00Z",
                |  "Article ID":"3f0ea549-a27a-4cfa-b4a4-f06c5cbb8231",
                |  "Author(s) name":["DANIEL LEBLANC"],
                |  "Freelancer/in-house/wire":["in-house"],
                |  "Popularity index":22.6,
                |  "Readership index":2000.7,
                |  "Total Engagement Time (Seconds)":2100,
                |  "Competition Score":4248930},
                | {"Article Name":"What Florida teaches us about gun violence",
                |  "Date Submitted":"2018-02-28T03:10:02Z",
                |  "Article ID":"da55d1f4-b4e1-4793-b2ef-280d77c7eebf",
                |  "Author(s) name":["Gary Smith","Weiwei Li"],
                |  "Freelancer/in-house/wire":["in-house","wire"],
                |  "Popularity index":12.5,
                |  "Readership index":72.3,
                |  "Total Engagement Time (Seconds)":560,
                |  "Competition Score":47488}]}
              """.stripMargin))
        case None => failure
      }
      }
    }

}
