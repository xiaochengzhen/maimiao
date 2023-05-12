package com.example.javabase.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author xiaobo
 * @description 可以自定义类型转换器定制，spring内部有一百多个默认的转换器
 * @date 2023/3/10 15:26
 */
@Component
public class StringToOrderConverter implements Converter<String, Order> {
    @Override
    public Order convert(String s) {
        Order user = new Order();
        return user;
    }
}
