package com.example.kafkaspringboot.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
//@Component
public class MyConfigConsumer {

    /**
     12 * @KafkaListener(groupId = "testGroup", topicPartitions = {
     13 * @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     14 * @TopicPartition(topic = "topic2", partitions = "0",
     15 * partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     16 * },concurrency = "6")
     17 * //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     18 * @param record
     19 */

    @KafkaListener(topics = "mytest",  concurrency = "1" ,containerFactory = "myListenerContainerFactory")
    public void receive1(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String key = record.key();
        log.info(key);
        String value = record.value();
        log.info(value);
        //手动提交
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "mytest1",  concurrency = "1" ,containerFactory = "myListenerContainerFactory2")
    public void receive2(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String key = record.key();
        log.info(key);
        String value = record.value();
        log.info(value);
        //手动提交
        acknowledgment.acknowledge();
    }

    /*@PostConstruct
    public void init() {
        // Dynamically set a unique group ID for each node
        String uniqueGroupId = generateUniqueGroupId();
        System.setProperty("spring.kafka.consumer.group-id", uniqueGroupId);
    }*/



}
