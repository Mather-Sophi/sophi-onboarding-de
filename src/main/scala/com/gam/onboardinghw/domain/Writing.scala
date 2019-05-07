package com.gam.onboardinghw.domain

trait Writing {

}

case object GetAuthors extends Writing
case class Authors(Authors: List[Author]) extends Writing

case class Article(
                    articleName: String,
                    articleId: String,
                    dateSubmitted: String,
                    authorName: String,
                    workStatus: String,
                    popularityIndex: Double,
                    readershipIndex: Double,
                    totalEngagementTime: Long
                  ) extends Writing

case class Author(
                   authorNames: String,
                   competitionScore: Double,
                   dateSubmitted: String
                 )  extends Writing