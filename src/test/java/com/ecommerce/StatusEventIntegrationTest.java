package com.ecommerce;

import com.ecommerce.dto.StatusEvent;
import com.ecommerce.model.StatusEventLog;
import com.ecommerce.repository.StatusEventRepository;
import com.ecommerce.service.KafkaConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("dev")
public class StatusEventIntegrationTest {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @SpyBean
    private StatusEventRepository statusEventRepository;

    @Test
    public void testStatusEventConsumptionAndRetrieval() throws Exception {
        // Arrange
        String resourceId = "test-resource-" + System.currentTimeMillis();
        StatusEvent event = StatusEvent.builder()
                .resourceType("ORDER")
                .resourceId(resourceId)
                .status("CREATED")
                .message("Manual trigger test")
                .timestamp(LocalDateTime.now())
                .build();

        // Act: Manually call the consumer method to simulate Kafka message arrival
        // This avoids dependency on a running Kafka broker for the unit/integration test
        kafkaConsumerService.consumeStatusEvent(event);

        // Assert: Verify it was saved to DB
        verify(statusEventRepository, timeout(5000)).save(any(StatusEventLog.class));
        
        List<StatusEventLog> logs = statusEventRepository.findByResourceIdOrderByTimestampDesc(resourceId);
        assertThat(logs).isNotEmpty();
        assertThat(logs.get(0).getMessage()).isEqualTo("Manual trigger test");
        assertThat(logs.get(0).getResourceId()).isEqualTo(resourceId);
    }
}
