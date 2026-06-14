package com.ecommerce.demo.client;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/demo/rest-template")
public class RestTemplateDemoController {

    private final RestTemplateDemoService restTemplateService;

    public RestTemplateDemoController(RestTemplateDemoService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/posts/{id}")
    public ExternalPostDTO getPost(@PathVariable Long id) {
        return restTemplateService.getPostById(id);
    }

    @GetMapping("/posts")
    public List<ExternalPostDTO> getAllPosts() {
        return restTemplateService.getAllPosts();
    }

    @PostMapping("/posts")
    public ExternalPostDTO createPost(@RequestBody ExternalPostDTO post) {
        return restTemplateService.createPost(post);
    }
}
