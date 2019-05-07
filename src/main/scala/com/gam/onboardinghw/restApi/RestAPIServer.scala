import java.sql.Timestamp

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.gam.onboardinghw.domain.{Article, Author, Authors, GetAuthors}
import com.gam.onboardinghw.util.Utils
import spray.json.DefaultJsonProtocol._

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.io.{Source, StdIn}

/**
  *
  */
object RestAPIServer {


  class WritingCompetition extends Actor with ActorLogging {

    var authors = List.empty[Author]
    def receive = {
      case GetAuthors => sender() ! Authors(Utils.generateAuthorList(Utils.readInputFile("sample-input.csv")))
      case _          => log.info("Invalid message")
    }
  }

  // these are from spray-json
  implicit val authorFormat = jsonFormat3(Author)
  implicit val authorsFormat = jsonFormat1(Authors)


  /**
    * main function
    * @param args
    */
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val writingCompetition =
      system.actorOf(Props[WritingCompetition], "writingCompetition")

    val route =
      path("writingCompetition") {
        get {
          implicit val timeout: Timeout = 5.seconds

          // query the actor for the current Author List

          val authors: Future[Authors] =
            (writingCompetition ? GetAuthors).mapTo[Authors]
          complete(authors)
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}
