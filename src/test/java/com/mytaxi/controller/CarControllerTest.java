package com.mytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainvalue.EngineType;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "mytaxi", password = "mytaxi")
public class CarControllerTest
{
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldFindCars() throws Exception
    {
        this.mockMvc.perform(get("/v1/cars"))
            .andExpect(status().isOk());
    }


    @Test
    public void shouldFindCarById() throws Exception
    {
        this.mockMvc.perform(get("/v1/cars/1"))
            .andExpect(status().isOk());
    }


    @Test
    @DirtiesContext
    public void shouldDeleteCar() throws Exception
    {
        this.mockMvc.perform(delete("/v1/cars/1"))
            .andExpect(status().isOk());
    }


    @Test
    public void shouldCreateACar() throws Exception
    {
        CarDTO car = CarDTO.newBuilder()
            .setConvertible(true)
            .setSeatCount(4)
            .setLicensePlate("XXXX-XXXX")
            .setEngineType(EngineType.ELETRIC)
            .setRating(5f)
            .setManufacturerDTO(ManufacturerDTO.newBuilder()
                .setName("TOYOTA")
                .createNewManufacturerDTO()).createCarDTO();
        String payload = new ObjectMapper()
            .writeValueAsString(car);

        this.mockMvc.perform(post("/v1/cars/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isCreated());
    }


    @Test
    public void shouldReturnBadRequestWhenManufacturerIsMissing() throws Exception
    {
        CarDTO car = CarDTO.newBuilder()
            .setConvertible(true)
            .setSeatCount(4)
            .setLicensePlate("XXXX-XXXX")
            .setEngineType(EngineType.ELETRIC)
            .setRating(5f)
            .createCarDTO();
        String payload = new ObjectMapper()
            .writeValueAsString(car);

        this.mockMvc.perform(post("/v1/cars/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnBadRequestWhenRatingGreaterThan5() throws Exception
    {
        CarDTO car = CarDTO.newBuilder()
            .setConvertible(true)
            .setSeatCount(4)
            .setLicensePlate("XXXX-XXXX")
            .setEngineType(EngineType.ELETRIC)
            .setRating(6f)
            .setManufacturerDTO(ManufacturerDTO.newBuilder()
                .setName("TOYOTA")
                .createNewManufacturerDTO()).createCarDTO();
        String payload = new ObjectMapper()
            .writeValueAsString(car);

        this.mockMvc.perform(post("/v1/cars/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnBadRequestWhenSeatCountIsNegativeValue() throws Exception
    {
        CarDTO car = CarDTO.newBuilder()
            .setConvertible(true)
            .setSeatCount(-4)
            .setLicensePlate("XXXX-XXXX")
            .setEngineType(EngineType.ELETRIC)
            .setRating(5f)
            .setManufacturerDTO(ManufacturerDTO.newBuilder()
                .setName("TOYOTA")
                .createNewManufacturerDTO()).createCarDTO();
        String payload = new ObjectMapper()
            .writeValueAsString(car);

        this.mockMvc.perform(post("/v1/cars/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnBadRequestWhenRatingIsNegative() throws Exception
    {
        CarDTO car = CarDTO.newBuilder()
            .setConvertible(true)
            .setSeatCount(4)
            .setLicensePlate("XXXX-XXXX")
            .setEngineType(EngineType.ELETRIC)
            .setRating(-5f)
            .setManufacturerDTO(ManufacturerDTO.newBuilder()
                .setName("TOYOTA")
                .createNewManufacturerDTO()).createCarDTO();
        String payload = new ObjectMapper()
            .writeValueAsString(car);

        this.mockMvc.perform(post("/v1/cars/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload))
            .andExpect(status().isBadRequest());
    }
}
