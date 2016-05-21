package net.joaoqalves

import akka.actor.{ActorLogging, Actor}
import kamon.metric.SubscriptionsDispatcher.TickMetricSnapshot

class KamonListenerActor extends Actor with ActorLogging {

  def receive = {
    case tickSnapshot: TickMetricSnapshot => log.info(tickSnapshot.metrics.toString())
  }
}
