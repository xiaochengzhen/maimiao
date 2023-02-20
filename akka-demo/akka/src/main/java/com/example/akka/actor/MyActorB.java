package com.example.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 16:55
 */
public class MyActorB extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,result->{
            System.out.println("Actor B 接收到消息："+ result);
        }).build();
    }

}
