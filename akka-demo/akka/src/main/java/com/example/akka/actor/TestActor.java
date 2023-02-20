package com.example.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 17:08
 */
public class TestActor {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("actorsystem");
        ActorRef actorRef = actorSystem.actorOf(Props.create(MyActorA.class));
        System.out.println(actorRef);
        //actorRef = akka://actorsystem/user/$a#424346408
        //远程调⽤
        ActorSelection actorSelection = actorSystem.actorSelection("akka://actorsystem/user/actorsystem");
        //⽀持通配符
        //ActorSelection actorSelection = actorSystem.actorSelection("akka://actorsystem/user/actorsystem*");
        actorSelection.tell("Message",ActorRef.noSender());
    }
}
