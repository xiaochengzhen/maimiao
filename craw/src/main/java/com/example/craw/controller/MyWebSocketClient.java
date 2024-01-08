package com.example.craw.controller;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
public class MyWebSocketClient extends WebSocketClient {
    private String name;
    private LongAdder longAdder;
    private int count;
    public MyWebSocketClient(URI serverUri, String name, LongAdder longAdder, int count) {
//        super(serverUri,new Draft_6455(),null, Integer.MAX_VALUE);
        super(serverUri);
        super.setConnectionLostTimeout(0);
        this.name = name;
        this.longAdder = longAdder;
        this.count = count;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connection opened.");
    }

    @Override
    public void onMessage(String message) {

        longAdder.add(1);
        if (longAdder.longValue() <= 2) {
            log.info("{}:接收到消息开始:{} {}", name, longAdder.longValue(), message);
        }
        if (longAdder.longValue() >= count - 2) {
            log.info("{}:接收到消息结束:{} {}", name, longAdder.longValue(), message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("关闭连接 名称:{} reason:{} remote:{}", name, reason, remote);
    }

    @Override
    public void onError(Exception e) {
        log.info(name + "发生异常", e);
    }


}