package sample.kamon

import akka.actor._
import RandomNumberActor._
import java.security.SecureRandom

/**
 * just doing some work
 */
class RandomNumberActor extends Actor {

  def receive = {
    case GenerateNumber =>
      val n = scala.math.random
    case GenerateSecureNumber =>
      val secure = new SecureRandom(System.currentTimeMillis.toHexString.getBytes)
      val n = secure.nextDouble
  }
}

object RandomNumberActor {

  case object GenerateNumber
  case object GenerateSecureNumber
}