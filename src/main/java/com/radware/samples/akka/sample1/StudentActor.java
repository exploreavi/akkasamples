package com.radware.samples.akka.sample1;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

public class StudentActor extends AbstractActor {

    public final LoggingAdapter log = Logging.getLogger(context().system(), getClass().getName());

    @Override
    public Receive createReceive() {
//        try {
            System.out.println("Message arrived from Teacher ");
//            return ReceiveBuilder.create().matchEquals(TeacherActor.class, p-> { getSender().tell("done", ActorRef.noSender());}).build();


            return ReceiveBuilder.create().matchEquals(TeacherActor.class,p-> { System.out.println("self: "+getSelf());}).
                    matchEquals("Hello", p-> {System.out.println("Hello");}).
                    build();
//        } catch(ActorKilledException ake) {
//            System.out.println("Student Actor killed");
//        }
        // exception during creation
//        return null;
    }

    private void helloMsg() {

    }

//    @Override
//    public void onReceive(Object message) throws Throwable {
//        sender().tell(getAnswer(message), self());
//    }
//
//    private Object getAnswer(final Object message) {
//        return "i am student";
//    }
//
//    public static Props props(final ActorRef teacher) {
//        return Props.create(Student.class, new Student(teacher));
//    }


}
