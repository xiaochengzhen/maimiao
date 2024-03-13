package com.example.redis1.lock.redisson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 存在问题，;服务宕机，死锁
 */
@RestController
public class RedisLock2Controller {
    private Object object = new Object();
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/lock2")
    public void test() {
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent("key", "clientID");
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
            stringRedisTemplate.delete("key");
        }

    }
}
