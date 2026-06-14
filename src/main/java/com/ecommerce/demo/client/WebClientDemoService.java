package com.ecommerce.demo.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientDemoService {

    private final WebClient webClient;

    /**
     * GET: Returns a Mono (zero or one item) asynchronously.
     */
    public Mono<ExternalPostDTO> getPostById(Long id) {
        String threadName = Thread.currentThread().getName();
        log.info("[ASYNC] [{}] 1. Start: Defining the WebClient pipeline", threadName);

        Mono<ExternalPostDTO> result = webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(ExternalPostDTO.class)
                .doOnNext(post -> {
                    String callbackThread = Thread.currentThread().getName();
                    log.info("[ASYNC] [{}] 3. DATA RECEIVED! Title: {}", callbackThread, post.getTitle());
                });

        log.info("[ASYNC] [{}] 2. Done: Pipeline defined, moving to NEXT LINE immediately!", threadName);
        return result;
    }

    /**
     * GET: Returns a Flux (zero or many items) asynchronously.
     */
    public Flux<ExternalPostDTO> getAllPosts() {
        log.info("[ASYNC] Requesting all posts...");
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(ExternalPostDTO.class)
                .doOnComplete(() -> log.info("[ASYNC] Completed fetching all posts"));
    }

    /**
     * POST: Create a post asynchronously.
     */
    public Mono<ExternalPostDTO> createPost(ExternalPostDTO post) {
        log.info("[ASYNC] Creating new post...");
        return webClient.post()
                .uri("/posts")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(ExternalPostDTO.class);
    }
}
