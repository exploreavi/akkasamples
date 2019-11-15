package com.radware.samples.akka.sample2;

import akka.actor.ActorCell;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class MonitorDemoApp {
  public static void main(String[] args) throws InterruptedException {
    ActorSystem system = ActorSystem.create("monitor");

//    ActorRef supervisor = system.actorOf(Props.create(SupervisorActor.class), "supervisor");
      ActorRef monitor = system.actorOf(Props.create(MonitorActor.class), "monitorActor");
      ActorRef supervisor = system.actorOf(SupervisorActor.props(), "supervisorActor");


      supervisor.tell("10", monitor);

      supervisor.tell(100, ActorRef.noSender());
      supervisor.tell(new Float(1.1), null);
    // ask supervisor result for the above event.

//    Integer result = null;
//    try {
//      result = (Integer) Await.result(
//        Patterns.ask(supervisor, new Result(), 5000),
//        Duration.create(5000, TimeUnit.MILLISECONDS));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    System.out.println("Value Received-> {} " + result);

//      supervisor.tell(new Integer(200), ActorRef.noSender());
//      supervisor.tell(new Result(),ActorRef.noSender());
//      system.stop(supervisor);

      Thread.sleep(5000);


      // terminate the actor system
      system.terminate();
  }
}
