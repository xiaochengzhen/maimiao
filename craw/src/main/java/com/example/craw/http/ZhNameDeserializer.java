package com.example.craw.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;
/**
 * @description name序列号类
 * @author xiaobo
 * @date 2023/12/4 8:15
 */
public class ZhNameDeserializer implements ObjectDeserializer{
    @Override
    public Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object ob = defaultJSONParser.parse();
        String obStr = ob==null?"":ob.toString();
        if (obStr.contains("zh_CN") || obStr.contains("en_US")) {
            return obStr;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zh_CN", obStr);
        return jsonObject.toJSONString();
    }
    @Override
    public int getFastMatchToken() {
        return 0;
    }

}
