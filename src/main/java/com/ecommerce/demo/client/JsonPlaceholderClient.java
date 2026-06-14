package com.ecommerce.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Feign Client to interact with the external JSONPlaceholder API.
 */
@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {

    @GetMapping("/posts")
    List<ExternalPostDTO> getAllPosts();

    @GetMapping("/posts/{id}")
    ExternalPostDTO getPostById(@PathVariable("id") Long id);

    @PostMapping("/posts")
    ExternalPostDTO createPost(@RequestBody ExternalPostDTO post);
}
