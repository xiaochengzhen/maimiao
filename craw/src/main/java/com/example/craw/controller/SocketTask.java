package com.example.craw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;

@Component
@Slf4j
public class SocketTask {
    final static List<MyWebSocketClient> allClient = new ArrayList<>();

    public int count = 0;

    public static void main(String[] args) throws IOException {

        int maxj = 50;
        int maxi = 400;

        LongAdder  longAdder = new LongAdder();

        for (int j = 0; j < maxj; j++) {
            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < maxi; i++) {
                        try {
                            MyWebSocketClient client = new MyWebSocketClient(new URI("ws://securities-ws.securities.svc.ebonex-newdev:8888/securities/wsData?sessionId="), "Name" + (finalJ *maxi + i), longAdder, maxj *maxi);
                            client.connect();
                            while (!client.isOpen()) {
                                Thread.sleep(100);
                            }
                            client.send("{\"ptc\":\"S\",\"command\":\"Q\",symbols:[\"00700.hk\",\"AAPL.us\"],\"timeMode\":\"0\"}\n");
                            allClient.add(client);
                        } catch (Exception e) {
                            log.error("连接异常", e);
                        }
                    }

                }
            }).start();
        }
        Timer timer = new Timer();

        // 创建一个任务（Runnable）并指定要执行的操作
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                System.out.println("清除任务开始");
                if (longAdder.longValue() >= maxj * maxi) {
                    longAdder.reset();
                }
//                System.out.println("清除任务结束");
            }
        };

        // 设置定时器开始计时的延迟时间为1000毫秒（1秒钟后开始执行任务）
        long delay = 1000;

        // 每次重复执行任务之前等待的时间间隔为2000毫秒（2秒钟）
        long interval = 1000;
        // 调度定时器任务
        timer.scheduleAtFixedRate(task, delay, interval);
    }
}
