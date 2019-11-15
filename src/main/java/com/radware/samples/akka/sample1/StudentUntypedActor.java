package com.radware.samples.akka.sample1;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

public class StudentUntypedActor extends UntypedAbstractActor {
    // no arg ctor is required for an untyped Abstract Actor
    public StudentUntypedActor() {
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Message: " + message);
    }
}
