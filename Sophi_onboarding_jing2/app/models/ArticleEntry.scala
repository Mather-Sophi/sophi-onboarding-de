package models

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json._


import scala.concurrent.duration.Duration
import scala.concurrent.duration.DurationInt
import scala.util.Try


/**
  * the case class represent an instance of each article parsed from one line of CSV file
  */

case class ArticleEntry(
                       articleName: String,
                       dateSubmitted: DateTime,
                       articleID: String,
                       authorName: Set[String],
                       freelancer: List[String],
                       popularityIndex: Double,
                       readershipIndex: Double,
                       totalEngagementTime: Duration,
                       competitionScore: Double
                       ) extends Comparable[ArticleEntry] {

  private val dateSubmittedFormatStr= "yyyy-MM-dd'T'HH:mm:ss'Z'"

  /**
    * This method concatenate competitionScore and dateSubmitted into a string for sorting
    * @param
    * @return the String result concatenate competitionScore and dateSubmitted
    */
  def getCombineKey: String = {
    val competitionScoreString = f"$competitionScore%.2f"
    competitionScoreString + "_" + dateSubmittedToString
  }

  /**
    * This method is help method to convert dateSubmitted DateTime to String
    * @param
    * @return the String convert from dateSubmitted
    */

  def dateSubmittedToString : String =
  {
    val fmt = DateTimeFormat.forPattern(dateSubmittedFormatStr)
    val dateSubmittedString = fmt.print(dateSubmitted)
    dateSubmittedString
  }

  /**
    * overriding the compareTo method so can sort by combine key
    *
    * @param o: ArticleEntry
    * @return the String convert from dateSubmitted
    */
  override def compareTo(o: ArticleEntry): Int = {
    val ret= if (o.getCombineKey > getCombineKey) -1 else if (o.getCombineKey < getCombineKey) 1 else 0
    ret
  }

}

object ArticleEntry {


  private val dateSubmittedFormatStr= "yyyy-MM-dd'T'HH:mm:ss'Z'"

  /**
    * This method is help method to convert a String to dateSubmitted DateTime
    * @param dateTimeText: String
    * @return the dateSubmitted DateTime convert from String
    */

  def toDateSubmitted(dateTimeText: String) : DateTime =
  {
    val fmt = DateTimeFormat.forPattern(dateSubmittedFormatStr)
    val dateSubmittedDT = Try(fmt.parseDateTime(dateTimeText)).getOrElse(fmt.parseDateTime("1900-01-01T00:00:00Z"))
    dateSubmittedDT
  }

  /**
    * This method is help method to convert a String to set of authors name
    * @param authorsNameText: String
    * @return the Set[String] split the string by semicolon
    */

  def toAuthorsName(authorsNameText: String) : Set[String] =
  {
    val authorNameSet = authorsNameText.split(";").map(_.trim).toSet[String]
    authorNameSet
  }

  /**
    * This method is help method to convert a String to list of freelancer
    * @param freelancerText: String
    * @return the List[String] split the string by semicolon
    */

  def toFreelancer(freelancerText: String) : List[String] =
  {
    val freelancerList = freelancerText.split(";").map(_.trim).toList
    freelancerList
  }

  /**
    * This method is help method to convert a String to popularityIndex
    * @param popularityIndexText: String
    * @return popularity: Double
    */

  def toPopularityIndex(popularityIndexText: String) : Double =
  {
    val popularityIndex = Try(popularityIndexText.toDouble).getOrElse(0.0)
    popularityIndex
  }


  /**
    * This method is help method to convert a String to readershipIndex
    * @param readershipIndexText: String
    * @return popularity: Double
    */

  def toReadershipIndex(readershipIndexText: String) : Double =
  {
    val readershipIndex = Try(readershipIndexText.toDouble).getOrElse(0.0)
    readershipIndex
  }

  /**
    * This method is help method to convert a String to totalEngagenmentTime duration
    * @param totalEngagementTimeText: String
    * @return the int stands totalEngagementTime in seconds
    */

  def toTotalEngagementTime(totalEngagementTimeText: String) : Duration =
  {
    val pat = """([0-9]+)\s*([A-Za-z]+)""".r
    val totalEngagementTime = totalEngagementTimeText match {
      case pat( theTime, "second") => theTime.toInt.second
      case pat( theTime, "seconds") => theTime.toInt.seconds
      case pat (theTime, "minute") => theTime.toInt.minute
      case pat (theTime, "minutes") => theTime.toInt.minutes
      case pat (theTime, "hour") => theTime.toInt.hour
      case pat (theTime, "hours") => theTime.toInt.hours
      case pat (theTime, "day") => theTime.toInt.day
      case pat (theTime, "days") => theTime.toInt.days
      case _  => 0.seconds
    }
    totalEngagementTime
  }



  /**
    * This method is help method to convert a String to totalEngagenmentTime duration
    * @param totalEngagementTime: Duration
    * @param popularityIndex: Double
    * @param readershipIndex: Double
    * @return the int stands totalEngagementTime in seconds
    */


  def toCompetitionScore(totalEngagementTime: Duration, popularityIndex: Double, readershipIndex: Double) : Double =
  {
    totalEngagementTime.toSeconds * (popularityIndex + readershipIndex)
  }

  def apply(
             articleName0: String,
             dateSubmitted0: String,
             articleID0: String,
             authorName0: String,
             freelancer0: String,
             popularityIndex0: String,
             readershipIndex0: String,
             totalEngagementTime0: String
           ): ArticleEntry =
    {
      val dateSubmitted = toDateSubmitted(dateSubmitted0)
      val authorName = toAuthorsName(authorName0)
      val freelancer = toFreelancer(freelancer0)
      val popularityIndex = toPopularityIndex(popularityIndex0)
      val readershipIndex = toReadershipIndex(readershipIndex0)
      val totalEngagementTime = toTotalEngagementTime(totalEngagementTime0)
      val competitionScore = toCompetitionScore(totalEngagementTime, popularityIndex, readershipIndex )

      new ArticleEntry(articleName0, dateSubmitted, articleID0, authorName, freelancer, popularityIndex, readershipIndex, totalEngagementTime, competitionScore)
    }

  /**
    * define customized Json Writer
    */

  implicit val articleEntryWrites = new Writes[ArticleEntry] {
    def writes(articleEntry: ArticleEntry) = Json.obj(
      "Article Name" -> articleEntry.articleName,
      "Date Submitted" -> articleEntry.dateSubmittedToString,
      "Article ID" -> articleEntry.articleID,
      "Author(s) name" ->  articleEntry.authorName.toSeq,
      "Freelancer/in-house/wire" -> articleEntry.freelancer,
      "Popularity index" -> articleEntry.popularityIndex,
      "Readership index" -> articleEntry.readershipIndex,
      "Total Engagement Time (Seconds)" -> articleEntry.totalEngagementTime.toSeconds,
      "Competition Score" -> articleEntry.competitionScore
    )
  }

}
