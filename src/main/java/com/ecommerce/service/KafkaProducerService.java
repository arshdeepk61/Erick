package com.ecommerce.service;

import com.ecommerce.dto.StatusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class KafkaProducerService {

    private static final String TOPIC = "status-events";

    @Autowired
    private KafkaTemplate<String, StatusEvent> kafkaTemplate;

    public void publishStatusEvent(String resourceType, String resourceId, String status, String message) {
        StatusEvent event = StatusEvent.builder()
                .resourceType(resourceType)
                .resourceId(resourceId)
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        
        log.info("Publishing status event: {}", event);
        try {
            kafkaTemplate.send(TOPIC, resourceId, event).get(5, java.util.concurrent.TimeUnit.SECONDS);
            log.info("Successfully published event to topic: {}", TOPIC);
        } catch (Exception e) {
            log.error("Failed to send Kafka event due to connection issue: {}. Error: {}", event, e.getMessage());
            // Rethrowing to make the failure visible during development/debugging
            throw new RuntimeException("Kafka message delivery failed", e);
        }
    }
}
