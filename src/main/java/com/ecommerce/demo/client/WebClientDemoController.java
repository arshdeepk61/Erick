package com.ecommerce.demo.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo/webclient")
@RequiredArgsConstructor
public class WebClientDemoController {

    private final WebClientDemoService webClientDemoService;

    /**
     * Note: Returning Mono/Flux allows Spring Boot to handle the async processing.
     * The connection remains open until the data is ready, but the thread is released!
     */

    @GetMapping("/posts")
    public Flux<ExternalPostDTO> getAllPosts() {
        return webClientDemoService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Mono<ExternalPostDTO> getPostById(@PathVariable Long id) {
        return webClientDemoService.getPostById(id);
    }

    @PostMapping("/posts")
    public Mono<ExternalPostDTO> createPost(@RequestBody ExternalPostDTO post) {
        return webClientDemoService.createPost(post);
    }
}
