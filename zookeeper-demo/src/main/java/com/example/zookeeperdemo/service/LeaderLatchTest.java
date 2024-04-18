package com.example.zookeeperdemo.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/13 18:44
 */
@Slf4j
public class LeaderLatchTest {

        LeaderLatch  leaderLatch;
        public static void main(String[] args) throws InterruptedException {
                LeaderLatchTest leaderLatchTest = new LeaderLatchTest();
                leaderLatchTest.setLeaderLatch("/self/fix3");
                TimeUnit.SECONDS.sleep(5000);
        }

        // 初始化 LeaderLatch
        public void setLeaderLatch(String path) {
                try {
                        CuratorFramework client = CuratorFrameworkFactory.builder().
                                connectString("zk-svc.securities.svc.ebonex-newdev:2381").
                              //  sessionTimeoutMs(50000).
                                retryPolicy(new RetryForever(100)).
                                build();
                        client.start();
                        String id = "client#" + InetAddress.getLocalHost().getHostAddress();
                        // client是具体的Curator客户端连接
                        leaderLatch = new LeaderLatch(client, path);
                        // isLeader 中的方法会在实例被选为主节点后被执行, 而notLeader中的不会被执行
                        // 如果主节点被失效, 会进行重新选主
                        LeaderLatchListener leaderLatchListener = new LeaderLatchListener() {
                                @Override
                                public void isLeader() {
                                        log.info("[LeaderLatch]我是主节点, id={}", leaderLatch.getId());
                                }
                                @Override
                                public void notLeader() {
                                        log.info("[LeaderLatch]我不是主节点, id={}", leaderLatch.getId());
                                }
                        };
                        leaderLatch.addListener(leaderLatchListener);
                        leaderLatch.start();
                } catch (Exception e) {
                        log.error("c创建LeaderLatch失败, path={}", path);
                }
        }

        // 判断实例是否是主节点
        public boolean hasLeadershipByLeaderLatch() {
                return leaderLatch.hasLeadership();
        }

        // 阻塞直到获得领导权
        public void awaitByLeaderLatch() {
                try {
                        leaderLatch.await();
                } catch (InterruptedException | EOFException e) {
                        e.printStackTrace();
                }
        }

        // 尝试获得领导权并超时
        public boolean awaitByLeaderLatch(long timeout, TimeUnit unit) {
                boolean hasLeadership = false;
                try {
                        hasLeadership = leaderLatch.await(timeout, unit);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                return  hasLeadership;
        }

}
