package com.radware.samples.akka.sample1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.sys.Prop;

import java.io.Serializable;

public class School {

    private ActorSystem system;
    private LoggingAdapter log;
    private ActorRef teacher;
    private  ActorRef student;
    private ActorRef unTypedStudentActor;

    School(ActorSystem system) {
        this.system = system;
        log = Logging.getLogger(system, getClass().getName());
        teacher = createTeacher();
        student = createStudent();
        unTypedStudentActor = createUntypedStudentActor();
    }

    private ActorRef createUntypedStudentActor() {
        // name must not contain space and some other special characters
        return system.actorOf(Props.create(StudentUntypedActor.class), "untypedstudent");
    }

    private ActorRef createTeacher() {
//        return system.actorOf(TeacherActor.props(student), "teacher");
        System.out.println("Create Teacher actor");

        return system.actorOf(Props.create(TeacherActor.class),"teacher");
    }

    private ActorRef createStudent() {
//        return system.actorOf(StudentActor.props(teacher), "student");
        System.out.println("Create Student actor");
        return system.actorOf(Props.create(StudentActor.class), "student");
    }

    public ActorRef getStudent() {
        return student;
    }

    public ActorRef getUnTypedStudentActor() {
        return unTypedStudentActor;
    }

    public ActorRef getTeacher() {
        return teacher;
    }
}
