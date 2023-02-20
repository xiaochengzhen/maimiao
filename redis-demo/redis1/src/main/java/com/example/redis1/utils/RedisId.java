package com.example.redis1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/16 16:48
 */
public class RedisId {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 开始时间戳，参考方法getTimesMap()
     */
    private static final long BEGIN_TIMESTAMP = 1645568542L;
    /**
     * 序列号的位数
     */
    private static final int COUNT_BITS = 32;
    /**
     * 自增前缀
     */
    private String keyPrefix = "Order";

    public void nextId() {
        // 1.生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;

        // 2.生成序列号
        // 2.1.获取当前日期，精确到天
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        // 2.2.自增长
        long count = stringRedisTemplate.opsForValue().increment("ID:" + keyPrefix + ":" + date);

        // 3.拼接
        long id = timestamp << COUNT_BITS | count;
        System.out.println(id);
    }

    /**
     * 得到某时间的时间戳
     */
    public void getTimesMap(){
        LocalDateTime localDateTime = LocalDateTime.of(2022, 2, 22, 22, 22, 22);
        System.out.println(localDateTime.toEpochSecond(ZoneOffset.UTC));
    }
}
