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
public class MyActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, result ->{
            //处理业务逻辑
            System.out.println("123");
        }).build();
    }

    public static void main(String[] args) {
        //定义一个Actor管理仓库
        ActorSystem system = ActorSystem.create("sys");
        //将⾃定义的 actor放⼊仓库并起个名字
        ActorRef actorDemo = system.actorOf(Props.create(MyActorA.class),"actorDemo");
        //发送消息
        actorDemo.tell("你好",ActorRef.noSender());
    }
}
