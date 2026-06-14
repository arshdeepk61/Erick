package com.ecommerce.demo.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo/comparison")
@RequiredArgsConstructor
@Slf4j
public class ComparisonDemoController {

    private final RestTemplateDemoService restTemplateService;
    private final WebClientDemoService webClientService;

    @GetMapping("/sync")
    public ExternalPostDTO testSync() {
        log.info("--- STARTING SYNC TEST ---");
        ExternalPostDTO data = restTemplateService.getPostById(1L);
        log.info("--- ENDING SYNC TEST ---");
        return data;
    }

    @GetMapping("/async")
    public Mono<ExternalPostDTO> testAsync() {
        log.info("--- STARTING ASYNC TEST ---");
        
        // 1. We start the async call
        Mono<ExternalPostDTO> data = webClientService.getPostById(1L);
        
        // 2. The code DOES NOT WAIT. It moves here immediately.
        log.info("--- DOING OTHER WORK (The code kept moving!) ---");
        for (int i = 1; i <= 3; i++) {
            log.info("Working on Task {}...", i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        
        log.info("--- ENDING ASYNC TEST (Method returned) ---");
        return data;
    }
}
