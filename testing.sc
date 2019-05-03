val str0 = "560 seconds"
val pat = """([0-9]+)\s*([A-Za-z]+)""".r

val pat(theTime1, theUnit1) = str0

val totalEngagementTime = str0 match {
  case pat( theTime, theUnit) if theUnit =="seconds" => theTime.toInt
  case pat (theTime, theUnit) if theUnit=="minutes" => theTime.toInt * 60
  //      case _  => 0
}
totalEngagementTime

val totalEngagementTime1 = str0 match {
  case pat( theTime, "seconds")  => theTime.toInt
  case pat (theTime, "minutes") => theTime.toInt * 60
  //      case _  => 0
}
totalEngagementTime1


import scala.concurrent.duration._
import scala.concurrent.duration.DurationInt
import scala.util.Try


val mytime = 560.seconds

mytime

val mytime2 = 35.minutes

val mytime3 = mytime2.toSeconds
//mytime2.toSeconds

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

val dateSubmittedFormatStr= "yyyy-MM-dd'T'HH:mm:ss'Z'"
val dateTimeText = "2018-02-28T03:10:02Z"
val fmt = DateTimeFormat.forPattern(dateSubmittedFormatStr)
val dateSubmittedDT = Try(fmt.parseDateTime(dateTimeText)).getOrElse(fmt.parseDateTime("1900-01-01T00:00:00Z"))
dateSubmittedDT

val dateSubmittedString = fmt.print(dateSubmittedDT)
dateSubmittedString






