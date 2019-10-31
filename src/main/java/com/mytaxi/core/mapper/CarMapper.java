package com.mytaxi.core.mapper;

import com.mytaxi.datatransferobject.CarCriteriaDTO;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{

    /**
     * Creates a {@link CarDTO}.
     * @param carDO {@link CarDO}
     * @return Returns a {@link CarDTO}
     */
    public static CarDTO makeCarDTO(CarDO carDO)
    {
        if (carDO == null)
        {
            return null;
        }
        ManufacturerDTO manufacturerDTO = ManufacturerMapper.makeManufacturerDTO(carDO.getManufacturerDO());
        return CarDTO.newBuilder()
            .setId(carDO.getId())
            .setConvertible(carDO.getConvertible())
            .setLicensePlate(carDO.getLicensePlate())
            .setEngineType(carDO.getEngineType())
            .setRating(carDO.getRating())
            .setSeatCount(carDO.getSeatCount())
            .setManufacturerDTO(manufacturerDTO)
            .createCarDTO();
    }


    /**
     *
     * @param carDTO
     * @return
     */
    public static CarDO makeDriverDO(CarDTO carDTO)
    {
        ManufacturerDO manufacturerDO = ManufacturerMapper.makeManufacturerDO(carDTO.getManufacturerDTO());
        return new CarDO(
            carDTO.getLicensePlate(),
            carDTO.getSeatCount(),
            carDTO.getConvertible(),
            carDTO.getRating(),
            carDTO.getEngineType(),
            manufacturerDO);
    }


    /**
     *
     * @param carCriteriaDTO
     * @return
     */
    public static CarDO makeDriverDO(CarCriteriaDTO carCriteriaDTO)
    {
        ManufacturerDO manufacturerDO = ManufacturerMapper.makeManufacturerDO(carCriteriaDTO.getManufacturerDTO());
        return new CarDO(
            carCriteriaDTO.getLicensePlate(),
            carCriteriaDTO.getSeatCount(),
            carCriteriaDTO.getConvertible(),
            carCriteriaDTO.getRating(),
            carCriteriaDTO.getEngineType(),
            manufacturerDO);
    }


    /**
     *
     * @param cars
     * @return
     */
    public static List<CarDTO> makeCarDTOList(List<CarDO> cars)
    {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
