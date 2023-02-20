package com.example.springboot04.springEL;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/10/14 13:15
 */
@Component
@Data
public class SpringEL {

    @Value("#{student.name?:'123'}")
    private String name;
}
