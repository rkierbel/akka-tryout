package com.example.printHierarchy;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class StartStopAct1 extends AbstractBehavior<String> {
  static Behavior<String> create() {
    return Behaviors.setup(StartStopAct1::new);
  }

  private StartStopAct1(ActorContext<String> context) {
    super(context);
    System.out.println("first started");

    context.spawn(StartStopAct2.create(), "second");
  }

  @Override
  public Receive<String> createReceive() {
    return newReceiveBuilder()
            .onMessageEquals("stop", Behaviors::stopped)
            .onSignal(PostStop.class, signal -> onPostStop())
            .build();
  }

  private Behavior<String> onPostStop() {
    System.out.println("first stopped");
    return this;
  }
}
