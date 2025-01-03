package com.example.kafkaspringboot.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Slf4j
//@Component
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

    @KafkaListener(topics = "test",   concurrency = "1")
    public void receive(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment) {
        for (ConsumerRecord<String, String> record : records) {
            log.info("===========================receive========================================{}", record.offset());
            String key = record.key();
            String value = record.value();
            if (value.equals("2")) {
                int i = 1 / 0;
            }
            log.info("====================================key={}=======value={}", key, value);
            //手动提交
            acknowledgment.acknowledge();
        }
    }

    /*@PostConstruct
    public void init() {
        // Dynamically set a unique group ID for each node
        String uniqueGroupId = generateUniqueGroupId();
        System.setProperty("spring.kafka.consumer.group-id", uniqueGroupId);
    }

    private String generateUniqueGroupId() {
        // Implement logic to generate a unique group ID
        // For example, use the node's hostname or a unique identifier
        return "unique-group-id-" + UUID.randomUUID().toString();
    }
*/
}
