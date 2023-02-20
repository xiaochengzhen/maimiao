package com.example.springboot04.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/17 11:21
 */
@Component
@Data
@ConfigurationProperties(prefix = "user")
@Validated // ConfigurationProperties支持 Validated 校验
public class User {
   // @Value("${user.age}")
    private Integer age;
    private String username;
    private List<String> list;
    private Map<Integer, String> map;
    private Address address;
}
