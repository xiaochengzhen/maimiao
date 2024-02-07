package com.example.study;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/19 17:10
 */
@Component
@Data
@ConfigurationProperties(prefix = "test")
public class Test {
    private String a;
    private String b;
}
