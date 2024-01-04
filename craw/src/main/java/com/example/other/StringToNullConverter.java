package com.example.other;
/**
 * @description 
 * @author xiaobo
 * @date 2023/12/27 15:19
 */
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToNullConverter implements Converter<String, String> {

    @Override
    public String convert(String source) {
        if (source != null && source.trim().isEmpty()) {
            return null;
        }
        return source;
    }
}

