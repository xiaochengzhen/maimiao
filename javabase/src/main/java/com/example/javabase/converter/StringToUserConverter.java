package com.example.javabase.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/10 15:26
 */
@Component
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String s) {
        User user = new User();
        if (!StringUtils.isEmpty(s)){
            String[] strings = StringUtils.delimitedListToStringArray(s, ":");
            user.setAge(Integer.valueOf(strings[0]));
            user.setName(strings[1]);
        }
        return user;
    }
}
