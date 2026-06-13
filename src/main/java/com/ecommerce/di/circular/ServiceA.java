package com.ecommerce.di.circular;

import com.ecommerce.di.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    private ServiceB serviceB;

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public String getMessage() {
        return "ServiceA calling " + serviceB.getName();
    }

    public String getName() {
        return "ServiceA";
    }
}
