package com.example.javabase.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author xiaobo
 * @description
 * spring boot json 处理是根据请求Content-Type: application/json 的时候有效果
 * 有三种方式，1、继承extends JsonObjectDeserializer<Maimiao> ，这个可以是都一个对象全局序列化
 * 2、如果是对单个字段的话可以再属性的加上@JsonDeserialize(using = MySerial.class)
 * 3、可以注入Jackson2ObjectMapperBuilderCustomizer ，如下，可以配置单个属性或者对象
 * 4、如果2和3冲突了，2优先，3不执行
 *
 * @date 2022/7/18 10:53
 */
@Configuration
public class JacksonCustomizerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){
        return builder -> {
            builder.serializerByType(String.class, new StringSerializer());
            builder.deserializerByType(String.class, new StringDeserializer());
        };
    }

    /**
     * 描述：将LocalDateTime转换为毫秒级时间戳
     */
    public static class StringSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null) {
                gen.writeNumber(value);
            }
        }
    }

    /**
     * 描述：将LocalDateTime转换为毫秒级时间戳
     */
    public static class StringDeserializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String text = jsonParser.getText();
            return text;
        }
    }

}
