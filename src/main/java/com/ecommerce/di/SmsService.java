package com.ecommerce.di;

import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsService implements MessageService {
    @Override
    public String getMessage() {
        return "Message from SmsService";
    }
}
