package com.radware.samples.akka.sample1;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import scala.io.StdIn;

public class App  {
    static class Counter extends AbstractLoggingActor {
        static class Message {
        }

        public Receive createReceive() {
            return ReceiveBuilder.create().match(Message.class,
                    this::onMessage).build();
        }

        private int counter = 0;

        private  void onMessage(Message message) {
            counter++;
            log().info("incremented counter " + counter);
        }


        public static Props props() {
            return Props.create(Counter.class);
        }
    }
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sample1");
        final ActorRef counter = system.actorOf(Counter.props(), "counter");
        counter.tell(new Counter.Message(), ActorRef.noSender());
        System.out.println("Press enter to terminate");
    }
}
