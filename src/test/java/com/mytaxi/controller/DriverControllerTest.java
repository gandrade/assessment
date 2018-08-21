package com.mytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.GeoCoordinate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WithMockUser(username = "mytaxi", password = "mytaxi")
public class DriverControllerTest
{

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnAllDrivers() throws Exception
    {
        this.mockMvc.perform(get("/v1/drivers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id", is(notNullValue())))
            .andExpect(jsonPath("$.length()", is(8)));
    }


    @Test
    public void shouldReturnASingleDriver() throws Exception
    {
        this.mockMvc.perform(get("/v1/drivers/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(notNullValue())));
    }


    @Test
    public void shouldCreateADriver() throws Exception
    {
        DriverDTO driverDTO = DriverDTO.newBuilder()
            .setUsername("driver-tst")
            .setPassword("driver-tst-pw")
            .createDriverDTO();
        String payload = new ObjectMapper().writeValueAsString(driverDTO);

        this.mockMvc.perform(post("/v1/drivers/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isCreated());
    }


    @Test
    public void shouldUpdateDriverLocation() throws Exception
    {
        GeoCoordinate coordinate = new GeoCoordinate(12, 12);
        this.mockMvc.perform(put("/v1/drivers/1")
            .param("longitude", String.valueOf(coordinate.getLongitude()))
            .param("latitude", String.valueOf(coordinate.getLatitude()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteDriver() throws Exception
    {
        DriverDTO driverDTO = DriverDTO.newBuilder()
            .setUsername("driver-to-be-deleted")
            .setPassword("driver-to-be-deleted")
            .createDriverDTO();
        String payload = new ObjectMapper().writeValueAsString(driverDTO);

        MvcResult mvcResult = this.mockMvc.perform(post("/v1/drivers/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isCreated()).andReturn();
        Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        this.mockMvc.perform(delete("/v1/drivers/" + id))
            .andExpect(status().isOk());
    }


    @Test
    public void shouldAssignCarForADriver() throws Exception
    {
        this.mockMvc.perform(put("/v1/drivers/8/cars/1/assign")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnCarAlreadyInUseAssigningCarForADriver() throws Exception
    {
        this.mockMvc.perform(put("/v1/drivers/8/cars/1/assign")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        this.mockMvc.perform(put("/v1/drivers/6/cars/1/assign")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict());
    }


    @Test
    public void shouldUnassignCarForADriver() throws Exception
    {
        this.mockMvc.perform(put("/v1/drivers/8/cars/1/deselect")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
