package com.example.redis5.service;

import com.alibaba.fastjson.JSON;
import com.example.redis5.mapper.ProductMapper;
import com.example.redis5.model.Product;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 1、如果商品数量很大，但是用户经常访问的数据是占比很少的，如果都存下来，浪费资源，所以要做冷热分离，为key设置超时时间, 查询的时候做续期
 * 2、缓存击穿，大量key同时过期导致缓存击穿,过期时间加上随机值
 * 3、缓存穿透，黑客用不存在的可以访问，导致缓存中没有，数据库中也没有，可以为不存在的商品在redis中设置空缓存
 * 4、冷门商品，突然大量并发请求，但是缓存中没有，可以用双重检测，重建缓存
 * 5、双写不一致
 *
 */
@Service
public class ProductServiceNew {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Redisson redisson;

    private static final String key = "product:cache:";
    private static final String empty_key = "empty";
    private static final int timeout = 24*60*60;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void create(Product product) {
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("index:lock" + product.getId());
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        try {
            productMapper.createProduct(product);
            stringRedisTemplate.opsForValue().set(key+product.getId(), JSON.toJSONString(product), getTimeout());
        } finally {
            rLock.unlock();
        }
    }

    public void update(Product product) {
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("index:lock" + product.getId());
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        try {
            productMapper.updateProduct(product);
            stringRedisTemplate.opsForValue().set(key + product.getId(), JSON.toJSONString(product), getTimeout());
        } finally {
            rLock.unlock();
        }
    }

    public Product getProduct(Integer id) {
        Product product = null;
        String s = stringRedisTemplate.opsForValue().get(key + id);
        product = JSON.parseObject(s, Product.class);
        if (product != null) {
            if (s.equals("empty")) {
                stringRedisTemplate.expire(key+id, getEmptyTimeout(), TimeUnit.SECONDS);
                return null;
            }
            stringRedisTemplate.expire(key+id, getTimeout(), TimeUnit.SECONDS);
            return product;
        }
        //重建缓存
        RLock lock = redisson.getLock("hot:lock" + id);
        lock.lock();
        try {
            s = stringRedisTemplate.opsForValue().get(key + id);
            product = JSON.parseObject(s, Product.class);
            if (product != null) {
                if (s.equals("empty")) {
                    stringRedisTemplate.expire(key+id, getEmptyTimeout(), TimeUnit.SECONDS);
                    return null;
                }
                stringRedisTemplate.expire(key+id, getTimeout(), TimeUnit.SECONDS);
                return product;
            }
            //解决双写不一致锁
            RReadWriteLock readWriteLock = redisson.getReadWriteLock("index:lock" + product.getId());
            RLock rLock = readWriteLock.readLock();
            rLock.lock();
            try {
                product = productMapper.getProduct(id);
                if (product == null) {
                    stringRedisTemplate.opsForValue().set(key + id, "empty", getEmptyTimeout());
                    return null;
                }
                stringRedisTemplate.opsForValue().set(key + id, JSON.toJSONString(product));
            } finally {
                rLock.unlock();
            }
        } finally {
            lock.unlock();
        }
        return product;
    }

    //防止同一时间大量key过期，导致缓存击穿
    private int getTimeout() {
        return timeout + new Random(5).nextInt()*60*60;
    }
    //空缓存过期时间，防止大量key存在
    private int getEmptyTimeout() {
        return 60 + new Random(5).nextInt()*60*60;
    }
}
