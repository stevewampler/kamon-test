package net.joaoqalves

import akka.actor.{Props, ActorSystem}
import kamon.Kamon
import kamon.akka.ActorMetrics

import net.joaoqalves.rest.InitActor

trait EngineApp {
  Kamon.start()

  implicit val system = ActorSystem("kamon-test")

  protected[this] val initActor = system.actorOf(Props(new InitActor()), "init-actor")

  val listener = system.actorOf(Props[KamonListenerActor], "kamon-listener")

  Kamon.metrics.subscribe(ActorMetrics.category, "*", listener, permanently = true)
  Kamon.tracer.subscribe(listener)

  def doInit() =  {
    val context = Kamon.tracer.newContext("sample-trace")

    initActor ! InitActor.Init

    context.finish()
  }
}
