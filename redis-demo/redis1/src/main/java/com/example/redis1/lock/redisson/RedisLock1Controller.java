package com.example.redis1.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 存在问题，如果是多节点，需要用分布式锁
 */
@RestController
public class RedisLock1Controller {
    private Object object = new Object();
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/lock1")
    public void test() {
        synchronized (object) {
            int count = Integer.parseInt(stringRedisTemplate.opsForValue().get("count"));
            if (count > 0) {
                int result = count - 1;
                stringRedisTemplate.opsForValue().set("count", result+"");
                System.out.println("库存还有"+result);
            } else {
                System.out.println("没有库存了");
            }
        }
    }


    @Autowired
    Redisson redisson;

    public void testLock() {
        RLock lock = redisson.getLock("123");
        lock.lock();
        try {
            System.out.println("");
        } finally {
            RLock unlock = redisson.getLock("123");
            unlock.unlock();
        }
    }

}
