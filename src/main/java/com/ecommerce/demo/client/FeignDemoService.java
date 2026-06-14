package com.ecommerce.demo.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignDemoService {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    public List<ExternalPostDTO> getAllPosts() {
        return jsonPlaceholderClient.getAllPosts();
    }

    public ExternalPostDTO getPostById(Long id) {
        return jsonPlaceholderClient.getPostById(id);
    }

    public ExternalPostDTO createPost(ExternalPostDTO post) {
        return jsonPlaceholderClient.createPost(post);
    }
}
