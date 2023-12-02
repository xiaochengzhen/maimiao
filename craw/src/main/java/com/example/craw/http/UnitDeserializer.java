package com.example.craw.http;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class UnitDeserializer implements ObjectDeserializer{
    @Override
    public Object deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object ob = defaultJSONParser.parse();
        if (ob != null) {
            String obStr = ob.toString();
            BigDecimal bigDecimal = null;
            if (obStr.contains("亿")) {
                String value = obStr.replaceAll("亿", "").replaceAll(",", "");
                bigDecimal = new BigDecimal(value).multiply(new BigDecimal(100000000));
            } else if (obStr.contains("万")) {
                String value = obStr.replaceAll("万", "").replaceAll(",", "");
                bigDecimal = new BigDecimal(value).multiply(new BigDecimal(10000));
            } else if (obStr.contains("千")) {
                String value = obStr.replaceAll("千", "").replaceAll(",", "");
                bigDecimal = new BigDecimal(value).multiply(new BigDecimal(1000));
            } else if (obStr.contains("百")) {
                String value = obStr.replaceAll("百", "").replaceAll(",", "");
                bigDecimal = new BigDecimal(value).multiply(new BigDecimal(100));
            }
            if (bigDecimal != null) {
                return bigDecimal;
            }
        }
        return ob;
    }
    @Override
    public int getFastMatchToken() {
        return 0;
    }

}
