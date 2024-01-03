package com.example.redis1.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 访问redis集群
 *
 * @author 图灵-诸葛老师
 */
public class JedisClusterTest {
    public static void main(String[] args) throws IOException {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.28.60", 8003));
        jedisClusterNode.add(new HostAndPort("192.168.28.61", 8002));
        jedisClusterNode.add(new HostAndPort("192.168.28.62", 8001));
        jedisClusterNode.add(new HostAndPort("192.168.28.60", 8006));
        jedisClusterNode.add(new HostAndPort("192.168.28.61", 8005));
        jedisClusterNode.add(new HostAndPort("192.168.28.62", 8004));

        JedisCluster jedisCluster = null;
        try {
            //connectionTimeout：指的是连接一个url的连接等待时间
            //soTimeout：指的是连接上一个url，获取response的返回等待时间
            jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "xiaobo", config);
            System.out.println(jedisCluster.set("cluster", "xiaobo"));
            System.out.println(jedisCluster.get("cluster"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisCluster != null)
                jedisCluster.close();
        }
    }
}
