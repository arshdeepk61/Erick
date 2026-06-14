package com.ecommerce.demo.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RestTemplateDemoService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    public RestTemplateDemoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // GET example: Fetch a single post
    public ExternalPostDTO getPostById(Long id) {
        String threadName = Thread.currentThread().getName();
        log.info("[SYNC] [{}] 1. Start: Calling External API...", threadName);
        
        String url = BASE_URL + "/" + id;
        ExternalPostDTO response = restTemplate.getForObject(url, ExternalPostDTO.class);
        
        log.info("[SYNC] [{}] 2. Done: API returned data", threadName);
        log.info("[SYNC] [{}] 3. Returning from method", threadName);
        return response;
    }

    // GET example: Fetch all posts (as list)
    public List<ExternalPostDTO> getAllPosts() {
        ExternalPostDTO[] posts = restTemplate.getForObject(BASE_URL, ExternalPostDTO[].class);
        return Arrays.asList(posts);
    }

    // POST example: Create a new post
    public ExternalPostDTO createPost(ExternalPostDTO newPost) {
        ResponseEntity<ExternalPostDTO> response = restTemplate.postForEntity(BASE_URL, newPost, ExternalPostDTO.class);
        return response.getBody();
    }
}
