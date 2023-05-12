package com.example.javabase.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/10 16:10
 */
//@JsonComponent
public class MySerial extends JsonObjectDeserializer<Maimiao> {
    @Override
    protected Maimiao deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        Maimiao maimiao = new Maimiao();
        maimiao.setName("xiaob");
        maimiao.setAge(10);
        return maimiao;
    }
}
