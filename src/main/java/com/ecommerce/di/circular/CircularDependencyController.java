package com.ecommerce.di.circular;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/di/circular")
public class CircularDependencyController {

    private final ServiceA serviceA;
    private final ServiceB serviceB;

    public CircularDependencyController(ServiceA serviceA, ServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

    @GetMapping("/demo")
    public Map<String, String> getCircularDemo() {
        Map<String, String> result = new HashMap<>();
        result.put("serviceA", serviceA.getMessage());
        result.put("serviceB", serviceB.getMessage());
        return result;
    }
}
