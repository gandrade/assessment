package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.EngineType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class CarMapperTest
{

    private ManufacturerDO manufacturerDO;


    @Before
    public void setup()
    {
        manufacturerDO = new ManufacturerDO("Tesla");
    }


    @Test
    public void shouldMakeCarDTO()
    {
        CarDO carDO = new CarDO("LICENSE-1231", 4, true, 2.2F, EngineType.ELETRIC, manufacturerDO);
        CarDTO carDTO = CarMapper.makeCarDTO(carDO);
        assertThat(carDTO.getId(), nullValue());
        assertThat(carDTO.getConvertible(), equalTo(true));
        assertThat(carDTO.getEngineType(), equalTo(EngineType.ELETRIC));
        assertThat(carDTO.getLicensePlate(), equalTo("LICENSE-1231"));
        assertThat(carDTO.getRating(), equalTo(2.2F));
        assertThat(carDTO.getSeatCount(), equalTo(4));
        assertThat(carDTO.getManufacturerDTO().getName(), equalTo("TESLA"));
    }


    @Test
    public void shouldMakeCarDTOWithNoSeatCount()
    {
        CarDO carDO = new CarDO("LICENSE-1231", null, true, 2.2F, EngineType.ELETRIC, manufacturerDO);
        CarDTO carDTO = CarMapper.makeCarDTO(carDO);
        assertThat(carDTO.getId(), nullValue());
        assertThat(carDTO.getConvertible(), equalTo(true));
        assertThat(carDTO.getEngineType(), equalTo(EngineType.ELETRIC));
        assertThat(carDTO.getLicensePlate(), equalTo("LICENSE-1231"));
        assertThat(carDTO.getRating(), equalTo(2.2F));
        assertThat(carDTO.getSeatCount(), nullValue());
        assertThat(carDTO.getManufacturerDTO().getName(), equalTo("TESLA"));
    }


    @Test
    public void shouldMakeCarDTOWithNoManufacturer()
    {
        CarDO carDO = new CarDO("LICENSE-1231", 4, true, 2.2F, EngineType.ELETRIC);
        CarDTO carDTO = CarMapper.makeCarDTO(carDO);
        assertThat(carDTO.getId(), nullValue());
        assertThat(carDTO.getConvertible(), equalTo(true));
        assertThat(carDTO.getEngineType(), equalTo(EngineType.ELETRIC));
        assertThat(carDTO.getLicensePlate(), equalTo("LICENSE-1231"));
        assertThat(carDTO.getRating(), equalTo(2.2F));
        assertThat(carDTO.getSeatCount(), equalTo(4));
        assertThat(carDTO.getManufacturerDTO().getName(), nullValue());
    }


    @Test
    public void shouldMakeCarDO()
    {
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO.ManufacturerDTOBuilder().setName("Tesla").createNewManufacturerDTO();
        CarDTO carDTO = new CarDTO.CarDTOBuilder()
            .setManufacturerDTO(manufacturerDTO)
            .setSeatCount(4)
            .setConvertible(true)
            .setEngineType(EngineType.ELETRIC)
            .setLicensePlate("LICENSE123").createCarDTO();
        CarDO carDO = CarMapper.makeDriverDO(carDTO);
        assertThat(carDO.getConvertible(), equalTo(true));
        assertThat(carDO.getSeatCount(), equalTo(4));
        assertThat(carDO.getEngineType(), equalTo(EngineType.ELETRIC));
        assertThat(carDO.getLicensePlate(), equalTo("LICENSE123"));
        assertThat(carDO.getManufacturerDO().getName(), equalTo("TESLA"));

    }
}
