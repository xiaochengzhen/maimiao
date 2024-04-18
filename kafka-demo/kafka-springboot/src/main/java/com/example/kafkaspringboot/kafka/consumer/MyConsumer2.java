package com.example.kafkaspringboot.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
//@Component
public class MyConsumer2 {

    /**
     12 * @KafkaListener(groupId = "testGroup", topicPartitions = {
     13 * @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     14 * @TopicPartition(topic = "topic2", partitions = "0",
     15 * partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     16 * },concurrency = "6")
     17 * //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     18 * @param record
     19 */

    @KafkaListener(topics = "test")
    public void receive(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment) {
        int idx = 0;
        try {
            //遍历所有消息
            for (ConsumerRecord<String, String> consumerRecord : records) {
                log.info("================================================{}", consumerRecord.offset());
                if (consumerRecord.value().equals("2")) {
                    //业务处理
                    int i = 1/0;
                }
                idx++;
            }
            // 全部处理完成时，确认消息
            acknowledgment.acknowledge();
            log.info("消费完毕");
        } catch (Throwable e) {
            // 拒绝消息列表中指定index（发生错误的消息index）对应的消息（此方法仅适用于listener.type=batch），
            // 当前指定index之前的消息会被成功提交，
            // 当前poll查询出的剩余消息记录（包括当前指定的index）均被抛弃，
            // 且当前消费线程在阻塞指定sleep（如下5000毫秒）后重新调用poll获取待消费消息（包括当前index及之前poll抛弃的消息）
            // 如下即确认当前list中前5条消息（0-4），抛弃当前list中后续消息，5秒后再次poll查询未消费消息
            acknowledgment.nack(idx, 1);
            log.error("消费异常", e);
        }
    }
}
