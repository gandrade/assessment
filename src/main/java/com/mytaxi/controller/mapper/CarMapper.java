package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;

public class CarMapper
{

    public static CarDTO makeCarDTO(CarDO carDO)
    {
        if (carDO == null)
        {
            return null;
        }
        return CarDTO.newBuilder()
            .setId(carDO.getId())
            .setConvertible(carDO.getConvertible())
            .setLicensePlate(carDO.getLicensePlate())
            .setEngineType(carDO.getEngineType())
            .setRating(carDO.getRating())
            .setSeatCount(carDO.getSeatCount())
            .setManufacturerDTO(ManufacturerMapper.makeManufacturerDTO(carDO.getManufacturerDO()))
            .createCarDTO();
    }


    public static CarDO makeDriverDO(CarDTO carDTO)
    {
        return new CarDO(
            carDTO.getLicensePlate(),
            carDTO.getSeatCount(),
            carDTO.getConvertible(),
            carDTO.getRating(),
            carDTO.getEngineType(),
            ManufacturerMapper.makeManufacturerDO(carDTO.getManufacturerDTO()));
    }
}
