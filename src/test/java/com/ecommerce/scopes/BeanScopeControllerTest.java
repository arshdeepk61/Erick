package com.ecommerce.scopes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BeanScopeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowScopes() throws Exception {
        mockMvc.perform(get("/scopes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.singleton").exists())
                .andExpect(jsonPath("$.application").exists())
                .andExpect(jsonPath("$.session").exists())
                .andExpect(jsonPath("$.request").exists())
                .andExpect(jsonPath("$.prototype_call_1").exists())
                .andExpect(jsonPath("$.prototype_call_2").exists());
    }

    @Test
    public void testScopeDemo() throws Exception {
        mockMvc.perform(get("/scope-demo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("What is a Spring \"bean scope\"?")));
    }
}
