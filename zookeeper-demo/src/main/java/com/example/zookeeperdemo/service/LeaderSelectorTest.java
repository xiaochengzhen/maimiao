package com.example.zookeeperdemo.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/13 18:41
 */
public class LeaderSelectorTest {
    private static final String ZK_ADDRESS = "zk-svc.securities.svc.ebonex-newdev:2381";
    private static final String ZK_PATH = "/self/fix";

    public static void main(String[] args) throws InterruptedException {

        LeaderSelectorListener selectorListener = new LeaderSelectorListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println(Thread.currentThread().getName() + " take leadership");
                //保留5s
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " give up leadership");
            }
        };

        Thread thread1 = new Thread(() -> {
            registerListener(selectorListener);
        });

        Thread thread2 = new Thread(() -> {
            registerListener(selectorListener);
        });

        Thread thread3 = new Thread(() -> {
            registerListener(selectorListener);
        });
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(5 * 60 * 1000);
    }

    private static void registerListener(LeaderSelectorListener listener) {
        //创建连接
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        //确保path存在
        try {
            client.create().creatingParentContainersIfNeeded().forPath(ZK_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //进行选举
        LeaderSelector leaderSelector = new LeaderSelector(client, ZK_PATH, listener);
        leaderSelector.autoRequeue();
        leaderSelector.start();
    }


}

