package com.example.printHierarchy;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;

public class ActorHierarchyExp {

  public static void main(String[] args) {
    ActorRef<String> testSystem = ActorSystem.create(Main.create(), "testSystem");
    testSystem.tell("start");


  }
}
