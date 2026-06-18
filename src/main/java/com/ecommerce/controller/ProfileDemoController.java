package com.ecommerce.controller;

import com.ecommerce.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileDemoController {

    private final MessageService messageService;
    private final String environmentName;

    public ProfileDemoController(MessageService messageService, 
                                 @Value("${app.environment:default}") String environmentName) {
        this.messageService = messageService;
        this.environmentName = environmentName;
    }

    @GetMapping("/info")
    public Map<String, String> getProfileInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("environment", environmentName);
        info.put("message", messageService.getMessage());
        return info;
    }
}
