package com.example.craw.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

public class EnNameDeserializer implements ObjectDeserializer{
    @Override
    public Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object ob = defaultJSONParser.parse();
        String obStr = ob==null?"":ob.toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zh_CN", obStr);
        return jsonObject.toJSONString();
    }
    @Override
    public int getFastMatchToken() {
        return 0;
    }

}
