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
        config.useSingleServer().setAddress("redis://192.168.28.60:8003").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
