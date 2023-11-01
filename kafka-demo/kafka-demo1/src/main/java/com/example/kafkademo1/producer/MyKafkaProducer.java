package com.example.kafkademo1.producer;

import org.apache.kafka.clients.producer.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Properties;
import java.util.concurrent.Future;

public class MyKafkaProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.28.60:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 1; i++) {
            producer.send(new ProducerRecord<String, String>("test12345", "key", Integer.toString(i)));
            Future<RecordMetadata> send = producer.send(new ProducerRecord<String, String>("test12345", "key", Integer.toString(i)));
            producer.send(new ProducerRecord<>("test12345", "key", Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        System.out.println("失败逻辑");
                    }
                }
            });
        }
        producer.close();
    }
}
