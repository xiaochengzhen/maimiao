package com.example.zookeeperdemo.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 17:02
 */
//@Component
public class ZkSchedulerFactoryBean  {

    private LeaderLatch leaderLatch;

    private final String LEADER_PATH = "/leader/left"; //namespace

    public ZkSchedulerFactoryBean() throws Exception {
        leaderLatch = new LeaderLatch(getClient(), LEADER_PATH);
        leaderLatch.addListener(new DemoLeaderLatchListener()); //当leader发生变化的时候，需要触发监听
        leaderLatch.start();
        TimeUnit.SECONDS.sleep(5);
        System.exit(0);
    }

    private CuratorFramework getClient() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("zk-svc.securities.svc.ebonex-newdev:2381")
                .sessionTimeoutMs(15000)
                .connectionTimeoutMs(20000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }


}
