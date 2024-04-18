package com.example.zookeeperdemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 17:03
 */
@Slf4j
public class DemoLeaderLatchListener implements LeaderLatchListener {

    @Override
    public void isLeader() {
       log.info(Thread.currentThread().getName()+"成为了leader");

    }

    @Override
    public void notLeader() {
        log.info(Thread.currentThread().getName()+"抢占leader失败，不执行任务");

    }
}

