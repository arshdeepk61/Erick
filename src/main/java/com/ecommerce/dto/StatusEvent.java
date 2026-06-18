package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusEvent {
    private String resourceType;
    private String resourceId;
    private String status;
    private String message;
    private LocalDateTime timestamp;
}
