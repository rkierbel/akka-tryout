package com.example.printHierarchy;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class StartStopAct2 extends AbstractBehavior<String> {
  static Behavior<String> create() {
    return Behaviors.setup(StartStopAct2::new);
  }

  private StartStopAct2(ActorContext<String> context) {
    super(context);
    System.out.println("second started");
  }

  @Override
  public Receive<String> createReceive() {
    return newReceiveBuilder().onSignal(PostStop.class, signal -> onPostStop()).build();
  }

  private Behavior<String> onPostStop() {
    System.out.println("second stopped");
    return this;
  }
}
