package com.example.kafkaspringboot.kafka.consumer;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/25 14:29
 */
@Component
public class GroupIdConfig {

    public String generateUniqueGroupId() {
        // Implement logic to generate a unique group ID
        // For example, use the node's hostname or a unique identifier
        return "unique-group-id-" + UUID.randomUUID().toString();
    }
}
