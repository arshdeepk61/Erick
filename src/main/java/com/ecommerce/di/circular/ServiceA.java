package com.ecommerce.di.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    @Autowired
    private ServiceB serviceB;

    public ServiceB getServiceB() {
        return serviceB;
    }

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
