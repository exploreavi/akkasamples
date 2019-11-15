package com.radware.samples.akka.sample2;

import akka.actor.*;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import static akka.actor.SupervisorStrategy.*;

public class SupervisorActor extends UntypedAbstractActor {

  private final ActorRef worker;

  public SupervisorActor() {
    this.worker = getContext().actorOf(Props.create(WorkerActor.class),"worker");
  }


  private static SupervisorStrategy strategy =
    new OneForOneStrategy(10, Duration.create(10, TimeUnit.SECONDS),
      new Function<Throwable, SupervisorStrategy.Directive>() {
        public SupervisorStrategy.Directive apply(Throwable t) {
          if (t instanceof ArithmeticException) {
            return resume();
          } else if (t instanceof
            NullPointerException) {
            return restart();
          } else if (t instanceof
            IllegalArgumentException) {
            return stop();
          } else {
            return escalate();
          }
        }
      });

  @Override
  public SupervisorStrategy supervisorStrategy() {
    return strategy;
  }

  @Override
  public void onReceive(Object message) throws Throwable {
    if(message instanceof String)
      getSender().tell(new RegisterWorker(worker, getSelf()), ActorRef.noSender());
    else
      worker.tell(message, getSelf());
  }

  public static Props props() {
        return Props.create(SupervisorActor.class);
  }

    public ActorRef getWorker() {
    return worker;
  }
}
