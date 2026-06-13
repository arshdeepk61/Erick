package com.ecommerce.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class LifecycleDemoBean {

    public LifecycleDemoBean() {
        System.out.println("[LIFECYCLE] 1. Constructor: Bean is being instantiated.");
    }

    /**
     * Executed AFTER the bean has been constructed and all dependencies 
     * (like @Autowired fields) have been injected.
     */
    @PostConstruct
    public void init() {
        System.out.println("[LIFECYCLE] 2. @PostConstruct: Initialization logic goes here (e.g., loading config).");
    }

    public void doWork() {
        System.out.println("[LIFECYCLE] 3. Bean is currently in use (Doing work).");
    }

    /**
     * Executed JUST BEFORE the bean is removed from the Spring container 
     * (usually when the application is shutting down).
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("[LIFECYCLE] 4. @PreDestroy: Cleanup logic goes here (e.g., closing connections).");
    }
}
