package com.gam.onboardinghw.restApi

import akka.actor.ActorSystem
import akka.testkit.{ ImplicitSender, TestActors, TestKit }
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }

class RestAPIServerSpec()
    extends TestKit(ActorSystem("writingCompetition"))
      with ImplicitSender
      with WordSpecLike
      with Matchers
      with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A writingCompetition actor" must {

    "send back messages with list of Authors" in {
      val echo = system.actorOf(TestActors.echoActorProps)
      echo ! "hello world"
      expectMsg("hello world")
    }

  }
}