package com.radware.samples.akka.sample2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WorkerActor extends UntypedAbstractActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  private int state = 0;

  @Override
  public void preStart() {
    log.info("Starting WorkerActor instance hashcode # {}",
      this.hashCode());
  }
  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof Integer) {
      state = (Integer) message;
      log.info("Integer value received: " + state);
    } else if (message instanceof Result) {
      getSender().tell(state, ActorRef.noSender());
    } else {
      // supervisor restarted this actor when any exception is thrown
      throw new IllegalArgumentException("Illegal argument");
    }
      System.out.println("Message after exception");
  }

  public static Props props() {
    return Props.create(WorkerActor.class);
  }

  @Override
  public void postStop() {
    log.info("Stopping WorkerActor instance hashcode # {}",
      this.hashCode());
  }
}
