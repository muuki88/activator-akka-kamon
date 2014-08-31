package sample.kamon

import akka.actor._
import scala.concurrent.duration._
import scala.math.random
import MessageGenerator._

/**
 * Defines different ways to create artifical traffic
 */
class MessageGeneratorActor extends Actor {

  val maxPeak = 10000
  val constantLoad = 500

  var peak: ActorRef = _
  var load: ActorRef = _

  override def preStart() {
    peak = context.actorOf(Props(classOf[PeakGenerator], maxPeak), "peak:generator")
    load = context.actorOf(Props(classOf[ConstantLoadGenerator], constantLoad), "load:generator")
  }

  def receive = {
    case Peak(s)         => peak ! s
    case ConstantLoad(s) => load ! s
  }
}

/**
 * Generating messages
 */
trait MessageGenerator extends Actor with ActorLogging {

  override def receive = {
    case Schedule(target, msg) => schedule(target, msg)
    case msg                   => log warning s"unkown: $msg"
    //    case _                     => // ignore answers
  }

  def schedule(target: ActorRef, message: Any)

}

/** the protocol */
object MessageGenerator {

  case class Peak[A](schedule: A)
  case class ConstantLoad(schedule: Schedule)

  case class Schedule(target: ActorRef, msg: Any)

}

/**
 * Generates peaks in random intervals
 * @param numMessages - how many messages should be in a peak
 */
class PeakGenerator(numMessages: Int) extends MessageGenerator {

  import context._

  def schedule(target: ActorRef, message: Any) {
    log info s"schedule peak $message"
    val wait = (random * 10.0).toLong
    system.scheduler.scheduleOnce(wait seconds, self, Schedule(target, message))
    for (i <- 0 to numMessages) {
      target ! message
    }
  }
}

/**
 * Generates a constant message load
 * @param msgPerSecond - how many messages should be sent per second
 */
class ConstantLoadGenerator(msgPerSecond: Int) extends MessageGenerator {

  import context._

  def schedule(target: ActorRef, message: Any) {
    system.scheduler.schedule(
      initialDelay = 0 seconds,
      interval = (1000 / msgPerSecond) milliseconds,
      receiver = target,
      message = message
    )
  }
}