package com.radware.samples.akka.sample1;

import akka.actor.*;

public class SchoolApp {
    public static void main(String[] args) {
        ActorSystem as = ActorSystem.create("njcs");
        School s = new School(as);

        // send normal message to the student actor
        s.getStudent().tell("Hello",null);

        // send Teacher's message to student
        s.getStudent().tell(TeacherActor.class, s.getTeacher());

        // send stop message to the actor
        s.getStudent().tell(PoisonPill.getInstance(),null);

        // send student actor kill message, student actor's receive method would throw Killed Exception
//        s.getStudent().tell(Kill.getInstance(),null);

        // explicitly stop an actor
//        as.stop(s.getStudent());

        // send message to untyped student actor
        StudentMessage msg = new StudentMessage();
        msg.setMsg("hello to untyped actor");
        s.getUnTypedStudentActor().tell(msg, ActorRef.noSender());

        // teminate the system
        as.terminate();
    }
}
