package com.radware.samples.akka.sample2;

import akka.actor.ActorRef;

public class RegisterWorker {

  private ActorRef worker;
  private ActorRef supervisor;

  public RegisterWorker(ActorRef worker, ActorRef supervisor) {
    this.worker = worker;
    this.supervisor = supervisor;
  }

  public RegisterWorker(ActorRef supervisor) {
    this.supervisor = supervisor;
  }


  public ActorRef getWorker() {
    return worker;
  }

  public ActorRef getSupervisor() {
    return supervisor;
  }
}
