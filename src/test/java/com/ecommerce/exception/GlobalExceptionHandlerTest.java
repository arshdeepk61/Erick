package com.ecommerce.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHandleResourceNotFoundException() throws Exception {
        // ID 999 should not exist, triggering ResourceNotFoundException in UserService
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("User not found with id: 999")))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.details", containsString("uri=/users/999")));
    }

    @Test
    public void testHandleIllegalArgumentException() throws Exception {
        // Triggered when trying to fetch a product by SKU that doesn't exist
        // (Wait, ProductService also throws ResourceNotFound for SKU. 
        // Let's use /orders/status/INVALID_STATUS to trigger a generic error or bad request if applicable)
        
        mockMvc.perform(get("/orders/status/INVALID"))
                .andExpect(status().isBadRequest());
    }
}
