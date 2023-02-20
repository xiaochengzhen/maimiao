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
public class MyActorA extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, result -> {
            System.out.println("Actor A 接收到消息：" + result);
            //由当前 Actor A 发送给 Actor B
            ActorRef actorref = getContext().actorOf(Props.create(MyActorB.class), "newactorb");
            actorref.tell("发送了消息", getSelf());
           // 获取原始的 Actor 转发给转发给
            //getSender().forward(result,getContext());
        }).build();
    }
}
