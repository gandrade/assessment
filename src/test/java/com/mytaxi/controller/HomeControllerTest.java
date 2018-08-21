package com.mytaxi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class HomeControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRenderSwaggerUI() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().is(302))
            .andExpect(redirectedUrl("swagger-ui.html"));
    }

    @Test
    public void shouldReturn403UnauthorizedCallingDriversMapping() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/drivers")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn403UnauthorizedCallingCarsMapping() throws Exception
    {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/cars")).andExpect(status().isUnauthorized());
    }
}
