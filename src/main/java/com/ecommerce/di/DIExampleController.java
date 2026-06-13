package com.ecommerce.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/di")
public class DIExampleController {

    // 1. Constructor Injection (Recommended)
    // Spring uses the @Primary EmailService here because no Qualifier is specified
    private final MessageService primaryService;

    // 2. Constructor Injection with @Qualifier
    private final MessageService smsService;

    // 3. Field Injection (Discouraged, but common in legacy code)
    // Field injection is hard to test because you can't easily inject a mock without Spring
    @Autowired
    @Qualifier("smsService")
    private MessageService fieldInjectedSmsService;

    // 4. Setter Injection
    // Useful for optional dependencies or when you need to change the dependency at runtime
    private MessageService setterInjectedService;

    @Autowired
    public DIExampleController(MessageService primaryService, 
                               @Qualifier("smsService") MessageService smsService) {
        this.primaryService = primaryService;
        this.smsService = smsService;
    }

    @Autowired
    public void setSetterInjectedService(MessageService setterInjectedService) {
        this.setterInjectedService = setterInjectedService;
    }

    @GetMapping("/demo")
    public Map<String, String> getDIDemo() {
        Map<String, String> responses = new HashMap<>();
        responses.put("constructorPrimary", primaryService.getMessage());
        responses.put("constructorSms", smsService.getMessage());
        responses.put("fieldSms", fieldInjectedSmsService.getMessage());
        responses.put("setterPrimary", setterInjectedService.getMessage());
        return responses;
    }
}
