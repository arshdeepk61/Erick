package com.ecommerce.aop;

import org.springframework.stereotype.Service;

@Service
public class AopDemoService {

    public String performTask() {
        return "Normal task performed";
    }

    public String performTaskWithException() {
        throw new RuntimeException("Something went wrong in the service!");
    }

    @TrackTime
    public String performTimedTask() throws InterruptedException {
        Thread.sleep(500); // Simulate some work
        return "Timed task performed";
    }
    
    public String performTaskWithArgs(String name, int value) {
        return "Task performed for " + name + " with value " + value;
    }
}
