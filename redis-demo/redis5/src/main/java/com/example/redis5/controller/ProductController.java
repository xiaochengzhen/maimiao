package com.example.redis5.controller;

import com.example.redis5.model.Product;
import com.example.redis5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @PostMapping("/update")
    public void update(@RequestBody Product product) {
        productService.update(product);
    }

    @GetMapping
    public Product getProduct(Integer id) {
        return productService.getProduct(id);
    }
}
