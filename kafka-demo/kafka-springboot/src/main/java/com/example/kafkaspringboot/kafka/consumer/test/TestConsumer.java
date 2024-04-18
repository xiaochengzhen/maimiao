package com.example.kafkaspringboot.kafka.consumer.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/25 16:06
 */
//@Configuration
public class TestConsumer {

    @KafkaListener(topics = "test",  groupId = "#{groupIdConfig.generateUniqueGroupId()}",containerFactory = "kafkaListenerContainerFactory1")
    public void listen1(String message) {
        // 处理消息
    }

    @KafkaListener(topics = "test1",  containerFactory = "kafkaListenerContainerFactory2")
    public void listen2(String message) {
        // 处理消息
    }

    @KafkaListener(topics = "test1")
    public void listen3(String message) {
        // 处理消息
    }

}
