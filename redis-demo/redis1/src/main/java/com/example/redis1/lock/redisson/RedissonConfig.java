package com.example.redis1.lock.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RedissonConfig {
    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
      //  config.useSingleServer().setAddress("redis://redis-svc.securities.svc.ebonex-newdev:4003").setDatabase(0);
        config.useClusterServers().addNodeAddress("redis://redis-svc.securities.svc.ebonex-newdev:4001", "redis://redis-svc.securities.svc.ebonex-newdev:4002",
                "redis://redis-svc.securities.svc.ebonex-newdev:4003").setSubscriptionConnectionPoolSize(1).setSubscriptionsPerConnection(1);
        return (Redisson) Redisson.create(config);
    }
}
