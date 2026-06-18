package com.ecommerce.controller;

import com.ecommerce.model.StatusEventLog;
import com.ecommerce.repository.StatusEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-events")
public class StatusEventController {

    @Autowired
    private StatusEventRepository statusEventRepository;

    @GetMapping
    public ResponseEntity<List<StatusEventLog>> getAllEvents() {
        return ResponseEntity.ok(statusEventRepository.findAll());
    }

    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<List<StatusEventLog>> getEventsByResourceId(@PathVariable String resourceId) {
        return ResponseEntity.ok(statusEventRepository.findByResourceIdOrderByTimestampDesc(resourceId));
    }

    @GetMapping("/type/{resourceType}")
    public ResponseEntity<List<StatusEventLog>> getEventsByResourceType(@PathVariable String resourceType) {
        return ResponseEntity.ok(statusEventRepository.findByResourceTypeOrderByTimestampDesc(resourceType));
    }
}
