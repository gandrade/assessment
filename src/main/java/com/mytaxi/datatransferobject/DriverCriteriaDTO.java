package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverCriteriaDTO
{
    @NotNull(message = "Username can not be null!")
    private String username;

    private GeoCoordinate coordinate;

    private OnlineStatus onlineStatus;

    @JsonDeserialize(builder = CarDTO.CarDTOBuilder.class)
    private CarCriteriaDTO carDTO = CarCriteriaDTO.newBuilder().createCarDTO();

    private DriverCriteriaDTO()
    {
    }


    private DriverCriteriaDTO(String username, GeoCoordinate coordinate, CarCriteriaDTO carDTO)
    {
        this.username = username;
        this.coordinate = coordinate;
        this.carDTO = carDTO;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    public String getUsername()
    {
        return username;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    @JsonIgnore
    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    @JsonProperty("car")
    public CarCriteriaDTO getCarDTO()
    {
        return carDTO;
    }

    public static class DriverDTOBuilder
    {
        private String username;
        private GeoCoordinate coordinate;
        private CarCriteriaDTO carDTO;


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }


        public DriverDTOBuilder setCarDTO(CarCriteriaDTO carDTO)
        {
            this.carDTO = carDTO;
            return this;
        }


        public DriverCriteriaDTO createDriverDTO()
        {
            return new DriverCriteriaDTO(username, coordinate, carDTO);
        }
    }
}
