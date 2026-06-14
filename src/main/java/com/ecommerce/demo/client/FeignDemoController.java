package com.ecommerce.demo.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo/feign")
@RequiredArgsConstructor
public class FeignDemoController {

    private final FeignDemoService feignDemoService;

    @GetMapping("/posts")
    public List<ExternalPostDTO> getAllPosts() {
        return feignDemoService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public ExternalPostDTO getPostById(@PathVariable Long id) {
        return feignDemoService.getPostById(id);
    }

    @PostMapping("/posts")
    public ExternalPostDTO createPost(@RequestBody ExternalPostDTO post) {
        return feignDemoService.createPost(post);
    }
}
