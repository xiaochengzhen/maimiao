package com.example.redis1.lock.redisson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 存在问题，;增加了超时时间，但是还有一个问题，如果过期了，锁自动释放了，其他线程就会获取锁，解锁也有问题，锁可以被其他线程解锁,增加了clientId uuid判断
 * 还要问题，超时时间问题，就要续期解决，redisson支持看门狗续期
 */
@RestController
public class RedisLock3Controller {
    private Object object = new Object();
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/lock3")
    public void test() {
        String clientId = UUID.randomUUID().toString();
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent("key", clientId, 30, TimeUnit.SECONDS);
        try {
            if (aBoolean) {
                int count = Integer.parseInt(stringRedisTemplate.opsForValue().get("count"));
                if (count > 0) {
                    int result = count - 1;
                    stringRedisTemplate.opsForValue().set("count", result+"");
                    System.out.println("库存还有"+result);
                } else {
                    System.out.println("没有库存了");
                }
            }
        } finally {
            String  clientIdS = stringRedisTemplate.opsForValue().get("key");
            if (clientId.equals(clientIdS)) {
                stringRedisTemplate.delete("key");
            }
        }

    }
}
