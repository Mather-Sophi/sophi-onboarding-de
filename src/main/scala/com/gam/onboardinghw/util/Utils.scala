package com.gam.onboardinghw.util

import com.gam.onboardinghw.domain.{Article, Author}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Utils extends LazyLogging {
  /**
    * Function to read article data from csv input file (comma seperated)
    * @param filePath
    * @return List[Article]
    *         {{{
    *         articlelist = readInputFile("/filepath/a.csv")
    *         }}}
    */
  def readInputFile(filePath: String): List[Article] = {
    val ArticleList = new ListBuffer[Article]()

    //val filePath = "sample-input.csv"
    val src   = Source.fromFile(filePath).getLines

    // assuming first line is a headers
    val headerLine = src.take(1).next

    // processing remaining lines
    for (l <- src) {
      // split line by comma and process them
      val line = l.split(",")

      val articleName = line(0)
      val dateSubmitted = line(1)
      val articleId = line(2)
      val authorName = line(3)
      val workStatus = line(4)
      val popularityIndex = line(5).toFloat
      val readershipIndex = line(6).toFloat

      val timeUnit = line(7).split(" ")(1).toLowerCase

      val totalEngagementTime = timeUnit match {
        case "seconds" => line(7).split(" ")(0).toLong
        case "minutes" => line(7).split(" ")(0).toLong * 60
        case "hours"   => line(7).split(" ")(0).toLong * 60 * 60
      }

      val article = Article(
        articleName,
        articleId,
        dateSubmitted,
        authorName,
        workStatus,
        popularityIndex,
        readershipIndex,
        totalEngagementTime
      )
      ArticleList += article
    }
    return ArticleList.toList
  }

  /**
    * Function to generate sorted Author list based on Competition Score descending
    * @param articleList
    * @return List[Author]
    */
  def generateAuthorList(articleList: List[Article]): List[Author] = {

    val authorList = new ListBuffer[Author]()
    authorList
    for (article <- articleList) {
      val authorName = article.authorName
      val competitionScore = article.totalEngagementTime * (article.popularityIndex + article.readershipIndex)
      val dateSubmitted = article.dateSubmitted
      val author = Author(
        authorName,
        competitionScore,
        dateSubmitted
      )
      authorList += author
    }

    // sorting by competition Score desc
    val sortedAuthorList = authorList.toList.sortBy(-_.competitionScore)

    return sortedAuthorList
  }

}
