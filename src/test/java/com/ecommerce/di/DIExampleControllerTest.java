package com.ecommerce.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class DIExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDIDemo() throws Exception {
        mockMvc.perform(get("/di/demo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.constructorPrimary", is("Message from EmailService (Primary)")))
                .andExpect(jsonPath("$.constructorSms", is("Message from SmsService")))
                .andExpect(jsonPath("$.fieldSms", is("Message from SmsService")))
                .andExpect(jsonPath("$.setterPrimary", is("Message from EmailService (Primary)")));
    }
}
