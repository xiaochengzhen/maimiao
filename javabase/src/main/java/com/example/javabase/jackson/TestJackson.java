package com.example.javabase.jackson;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/8 08:19
 */
public class TestJackson {


    public static void main(String[] args) throws IOException {
        Maimiao maimiao = new Maimiao();
        maimiao.setAge(1);
        maimiao.setName("maimiao");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(maimiao);
        mapper.enable(SerializationFeature.EAGER_SERIALIZER_FETCH);
        System.out.println(s);
        String ss = "{\"age\":1,\"name\":\"maimiao\"}";
        Maimiao maimiao1 = mapper.readValue(ss, Maimiao.class);
        System.out.println(maimiao1.toString());
        JsonNode jsonNode = mapper.readTree(ss);
        JsonNode name = jsonNode.get("name");
        String s1 = name.textValue();
        System.out.println(s1);

        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(new File("student.json"), JsonEncoding.UTF8);
        generator.writeStartObject();
        generator.writeStringField("name", "xiaob");
        generator.writeEndObject();
        generator.close();

        Map map = mapper.readValue(new File("student.json"), Map.class);
        System.out.println(map.get("name"));
        System.out.println("===================");

        JsonParser parser = jsonFactory.createParser(new File("student.json"));
        while (parser.nextToken() != JsonToken.END_OBJECT) {
         //   System.out.println(parser.getCurrentToken());
            System.out.println(parser.getCurrentName());
         //   System.out.println(parser.getCurrentValue());
            System.out.println(parser.getText());
            parser.nextToken();
        }

    }
}
