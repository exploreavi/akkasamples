package com.radware.samples.akka.sample1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.io.dns.internal.DnsClient;
import akka.japi.pf.ReceiveBuilder;
import scala.sys.Prop;

public class TeacherActor extends AbstractActor {
    private String name;
    public final LoggingAdapter log = Logging.getLogger(context().system(), getClass().getName());

//    @Override
//    public void onReceive(Object message) throws Throwable {
//        sender().tell(getAnswer(message), self());
//    }

//    private Object getAnswer(final Object message) {
//        return "i am teacher";
//    }
//
//    public static Props props(final ActorRef student) {
//        return Props.create(Teacher.class, new Teacher(student));
//    }

    @Override
    public Receive createReceive() {
        System.out.println("Student's response: ");
        return ReceiveBuilder.create().build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
