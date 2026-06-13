package com.ecommerce.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailService implements MessageService {
    @Override
    public String getMessage() {
        return "Message from EmailService (Primary)";
    }
}
