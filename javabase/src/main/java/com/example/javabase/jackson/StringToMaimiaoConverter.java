package com.example.javabase.jackson;

import com.example.javabase.converter.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/10 15:26
 */
@Component
public class StringToMaimiaoConverter implements Converter<String, Maimiao> {
    @Override
    public Maimiao convert(String s) {
        Maimiao maimiao = new Maimiao();
        if (!StringUtils.isEmpty(s)){
            String[] strings = StringUtils.delimitedListToStringArray(s, ":");
            maimiao.setAge(Integer.valueOf(strings[0]));
            maimiao.setName(strings[1]);
        }
        return maimiao;
    }
}
