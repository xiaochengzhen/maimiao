package com.example.kafkaspringboot.kafka.consumer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MyConsumer {

    /**
     12 * @KafkaListener(groupId = "testGroup", topicPartitions = {
     13 * @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     14 * @TopicPartition(topic = "topic2", partitions = "0",
     15 * partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     16 * },concurrency = "6")
     17 * //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     18 * @param record
     19 */

    @KafkaListener(topics = "test", groupId = "xxx", concurrency = "3")
    public void receive(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String key = record.key();
        log.info(key);
        String value = record.value();
        log.info(value);
        //手动提交
        acknowledgment.acknowledge();
    }
}
