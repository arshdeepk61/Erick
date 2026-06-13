package com.ecommerce.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aop")
public class AopDemoController {

    private final AopDemoService aopDemoService;

    public AopDemoController(AopDemoService aopDemoService) {
        this.aopDemoService = aopDemoService;
    }

    @GetMapping("/demo")
    public Map<String, String> getAopDemo() {
        Map<String, String> response = new HashMap<>();
        response.put("normal", aopDemoService.performTask());
        return response;
    }

    @GetMapping("/demo/args")
    public Map<String, String> getAopDemoWithArgs(@RequestParam String name, @RequestParam int value) {
        Map<String, String> response = new HashMap<>();
        response.put("args", aopDemoService.performTaskWithArgs(name, value));
        return response;
    }

    @GetMapping("/demo/timed")
    public Map<String, String> getAopDemoTimed() throws InterruptedException {
        Map<String, String> response = new HashMap<>();
        response.put("timed", aopDemoService.performTimedTask());
        return response;
    }

    @GetMapping("/demo/exception")
    public Map<String, String> getAopDemoException() {
        Map<String, String> response = new HashMap<>();
        try {
            response.put("exception", aopDemoService.performTaskWithException());
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}
