package com.radware.samples.akka.sample2;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.HashMap;
import java.util.Map;

public class MonitorActor extends UntypedAbstractActor {
  LoggingAdapter logger = Logging.getLogger(getContext().system(), this);
  Map<ActorRef, ActorRef> monitoredActors = new HashMap<>();

  @Override
  public void onReceive(Object message) throws Throwable {
    if (message instanceof Terminated) {
      final Terminated t = (Terminated)message;
      if(monitoredActors.containsKey(t.getActor())) {
        logger.info("Received termination request for actor " + t.getActor().path());
        logger.info("Sending information to supervisor ");
        monitoredActors.get(t.getActor()).tell(new DeadWorker(), ActorRef.noSender());
      }
    } else if (message instanceof RegisterWorker) {
      RegisterWorker msg = (RegisterWorker)message;
      System.out.println("Registering sender " + msg.getWorker() + " " + msg.getSupervisor());
      getContext().watch(msg.getWorker());
      monitoredActors.put(msg.getWorker(),msg.getSupervisor());
//       System.out.println("Worker is" + msg.getSupervisor());
//       getContext().watch(msg.getSupervisor());
//      monitoredActors.put(msg.getSupervisor(),msg.getSupervisor());
    } else {
      System.out.println("Invalid passed");
      unhandled(message);
    }
  }
}
