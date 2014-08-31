package sample.kamon

import akka.actor._
import MessageGenerator._
import RandomNumberActor._

object Main extends App {

  val system = ActorSystem("application")

  val numberGenerator = system.actorOf(Props[RandomNumberActor], "numbers")

  val generator = system.actorOf(Props[MessageGeneratorActor], "artifical")

  generator ! ConstantLoad(Schedule(numberGenerator, GenerateNumber))
  generator ! ConstantLoad(Schedule(numberGenerator, GenerateSecureNumber))
  generator ! Peak(Schedule(numberGenerator, GenerateNumber))
  generator ! Peak(Schedule(numberGenerator, GenerateSecureNumber))
}