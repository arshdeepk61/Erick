package com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lifecycle")
public class LifecycleDemoController {

    @Autowired
    private LifecycleDemoBean lifecycleDemoBean;

    @GetMapping("/test")
    public Map<String, String> testLifecycle() {
        // Trigger a method in the bean to show it is active
        lifecycleDemoBean.doWork();
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "Active");
        response.put("message", "Controller is now running from the CONFIG folder!");
        return response;
    }
}
