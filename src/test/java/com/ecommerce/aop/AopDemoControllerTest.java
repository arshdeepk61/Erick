package com.ecommerce.aop;

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
public class AopDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAopNormal() throws Exception {
        mockMvc.perform(get("/aop/demo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.normal", is("Normal task performed")));
    }

    @Test
    public void testAopWithArgs() throws Exception {
        mockMvc.perform(get("/aop/demo/args?name=Junie&value=100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.args", is("Task performed for Junie with value 100")));
    }

    @Test
    public void testAopTimed() throws Exception {
        mockMvc.perform(get("/aop/demo/timed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timed", is("Timed task performed")));
    }

    @Test
    public void testAopException() throws Exception {
        mockMvc.perform(get("/aop/demo/exception"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", is("Something went wrong in the service!")));
    }
}
