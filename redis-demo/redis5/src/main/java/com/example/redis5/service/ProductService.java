package com.example.redis5.service;

import com.example.redis5.mapper.ProductMapper;
import com.example.redis5.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    private static final String key = "product:cache:";

    @Autowired
    private RedisTemplate redisTemplate;

    public void create(Product product) {
        productMapper.createProduct(product);
        redisTemplate.opsForValue().set(key+product.getId(), product);
    }

    public void update(Product product) {
        productMapper.updateProduct(product);
        redisTemplate.opsForValue().set(key+product.getId(), product);
    }

    public Product getProduct(Integer id) {
        Product product = null;
        product = (Product) redisTemplate.opsForValue().get(key+id);
        if (product != null) {
            return product;
        }
        product =  productMapper.getProduct(id);
        redisTemplate.opsForValue().set(key+id, product);
        return product;
    }
}
