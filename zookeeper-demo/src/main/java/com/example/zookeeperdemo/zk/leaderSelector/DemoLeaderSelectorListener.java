package com.example.zookeeperdemo.zk.leaderSelector;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 17:03
 */
@Slf4j
public class DemoLeaderSelectorListener implements LeaderSelectorListener {


    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        log.info("============当takeLeadership()方法结束后就会自动释放领导权。================");
    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        log.info("============================");
    }
}

