package models

import org.joda.time.DateTime
import org.specs2.mutable._

import scala.concurrent.duration.Duration
//import models.ArticleEntry

class ArticleEntryTest extends Specification {
  "the first ArticleEntry" should {
    """build first ArticleEntry using apply method """ in {
      val article1 = ArticleEntry("What Florida teaches us about gun violence", "2018-02-28T03:10:02Z",
        "da55d1f4-b4e1-4793-b2ef-280d77c7eebf", "Gary Smith;Weiwei Li", "in-house;wire", "12.5", "72.3", "560 seconds")
      article1.articleName must be_==("What Florida teaches us about gun violence")
      article1.dateSubmittedToString must be_==("2018-02-28T03:10:02Z")
      article1.articleID must be_==("da55d1f4-b4e1-4793-b2ef-280d77c7eebf")
      article1.authorName must have size (2)
      article1.freelancer must have size (2)
      article1.popularityIndex must be_==(12.5)
      article1.readershipIndex must be_==(72.3)
      article1.totalEngagementTime.toSeconds must be_==(560)
      article1.competitionScore must be_==(560*( 12.5+72.3))
    }
  }

  "the second ArticleEntry" should {
    """build second ArticleEntry using apply method """ in {
      val article1 = ArticleEntry("Federal government to consult public on banning handguns", "2018-02-29T06:14:05Z",
        "3f0ea549-a27a-4cfa-b4a4-f06c5cbb8231", "DANIEL LEBLANC", "in-house", "22.6", "2000.7", "35 minutes")
      article1.articleName must be_==("Federal government to consult public on banning handguns")
      article1.dateSubmittedToString must be_==("1900-01-01T00:00:00Z")
      article1.articleID must be_==("3f0ea549-a27a-4cfa-b4a4-f06c5cbb8231")
      article1.authorName must have size (1)
      article1.freelancer must have size (1)
      article1.popularityIndex must be_==(22.6)
      article1.readershipIndex must be_==(2000.7)
      article1.totalEngagementTime.toSeconds must be_==(35*60)
      article1.competitionScore must be_==((35*60)*(22.6+2000.7))
    }

  }
}