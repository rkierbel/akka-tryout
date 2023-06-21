package com.example.printHierarchy;

import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Main extends AbstractBehavior<String> {

  static Behavior<String> create() {
    return Behaviors.setup(Main::new);
  }

  private Main(ActorContext<String> context) {
    super(context);
  }

  @Override
  public Receive<String> createReceive() {
    return newReceiveBuilder().onMessageEquals("start", this::start).build();
  }

  private Behavior<String> start() {
    ActorRef<String> firstRef = getContext().spawn(PrintMyActorRef.create(), "first-actor");

    System.out.println("First: " + firstRef);
    firstRef.tell("printit");

    ActorRef<String> first = getContext().spawn(StartStopAct1.create(), "first");
    first.tell("stop");

    ActorRef<String> supervisingActor =
            getContext().spawn(SupervisingActor.create(), "supervising-actor");
    supervisingActor.tell("failChild");
    return Behaviors.same();
  }
}
