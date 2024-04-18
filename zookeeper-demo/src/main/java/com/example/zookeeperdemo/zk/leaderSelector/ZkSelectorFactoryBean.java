package com.example.zookeeperdemo.zk.leaderSelector;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 17:02
 */
//@Component
public class ZkSelectorFactoryBean {

    private LeaderSelector leaderSelector;

    private final String LEADER_PATH = "/leader/selector"; //namespace

    public ZkSelectorFactoryBean() throws Exception {
        leaderSelector = new LeaderSelector(getClient(), LEADER_PATH, new DemoLeaderSelectorListener());
        leaderSelector.autoRequeue();
        leaderSelector.start();
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
