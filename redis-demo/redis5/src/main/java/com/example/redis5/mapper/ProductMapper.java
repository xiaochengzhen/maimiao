package com.example.redis5.mapper;

import com.example.redis5.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {

    Product getProduct(Integer id);

    int updateProduct(Product product);

    int createProduct(Product product);
}
