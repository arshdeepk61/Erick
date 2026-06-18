package com.ecommerce.service;

import com.ecommerce.dto.StatusEvent;
import com.ecommerce.model.StatusEventLog;
import com.ecommerce.repository.StatusEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    private final StatusEventRepository statusEventRepository;

    public KafkaConsumerService(StatusEventRepository statusEventRepository) {
        this.statusEventRepository = statusEventRepository;
    }

    @KafkaListener(topics = "status-events", groupId = "ecommerce-group")
    public void consumeStatusEvent(StatusEvent event) {
        log.info("Received status event: {}", event);
        
        try {
            StatusEventLog logEntry = StatusEventLog.builder()
                    .resourceType(event.getResourceType())
                    .resourceId(event.getResourceId())
                    .status(event.getStatus())
                    .message(event.getMessage())
                    .timestamp(event.getTimestamp())
                    .build();
            
            statusEventRepository.save(logEntry);
            log.info("Successfully saved status event to database");
        } catch (Exception e) {
            log.error("Failed to save status event to database: {}", e.getMessage());
        }
    }
}
