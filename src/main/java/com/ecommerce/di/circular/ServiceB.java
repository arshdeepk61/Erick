package com.ecommerce.di.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    private ServiceA serviceA;

    @Autowired
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public String getMessage() {
        return "ServiceB calling " + serviceA.getName();
    }

    public String getName() {
        return "ServiceB";
    }
}
