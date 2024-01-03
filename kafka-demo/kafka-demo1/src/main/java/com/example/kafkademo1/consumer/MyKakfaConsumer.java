package com.example.kafkademo1.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import javax.security.auth.callback.Callback;
import java.time.Duration;
import java.util.*;

public class MyKakfaConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10 * 1000);
        props.setProperty("bootstrap.servers", "192.168.28.60:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //消费主体
        consumer.subscribe(Arrays.asList("test12345"));

        //消费主题下的指定分区
        consumer.assign(Arrays.asList(new TopicPartition("test12345", 0)));

        //从头消费
        consumer.assign(Arrays.asList(new TopicPartition("test12345", 0)));
        consumer.seekToBeginning(Arrays.asList(new TopicPartition("test12345", 0)));

        //指定offset消费
        consumer.assign(Arrays.asList(new TopicPartition("test12345", 0)));
        consumer.seek(new TopicPartition("test12345", 0),10);

        //指定时间的消费
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("test12345");
        //一个小时前
        Long  time = new Date().getTime() - 1000*60*60L;
        Map<TopicPartition, Long> map = new HashMap<>();
        for (PartitionInfo partitionInfo : partitionInfos) {
            map.put(new TopicPartition("test12345", partitionInfo.partition()), time);
        }
        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = consumer.offsetsForTimes(map);
        Set<Map.Entry<TopicPartition, OffsetAndTimestamp>> entries = topicPartitionOffsetAndTimestampMap.entrySet();
        for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : entries) {
            TopicPartition key = entry.getKey();
            OffsetAndTimestamp value = entry.getValue();
            long offset = value.offset();
            consumer.assign(Arrays.asList(key));
            consumer.seek(key, offset);
        }

        while (true) {
            //poll长轮训，100ms内一直去poll，如果一次1ms，如果有消息，走之后逻辑，如果没有，继续poll，知道100用完
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
            if (!records.isEmpty()) {
                //同步提交，要把offset提交到服务端之后才会走下面逻辑，一把用同步提交，因为后面也没有什么业务逻辑了
                consumer.commitAsync();
                //异步提交
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        if (e != null) {
                            System.out.println("提交失败处理逻辑");
                        }
                    }
                });
            }
        }

    }
}
