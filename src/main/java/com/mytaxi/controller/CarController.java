package com.mytaxi.controller;

import com.mytaxi.core.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RequestMapping("v1/cars")
@RestController
public class CarController
{

    private final CarService carService;


    public CarController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping
    public List<CarDTO> getCars()
    {
        List<CarDO> cars = carService.findAll();
        return CarMapper.makeCarDTOList(cars);
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        CarDO carDO = carService.find(carId);
        return CarMapper.makeCarDTO(carDO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
        CarDO carDO = CarMapper.makeDriverDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }


    @PutMapping("/{carId}")
    public void updateCar(
        @Valid @PathVariable long carId,
        @Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
        carService.update(carId, CarMapper.makeDriverDO(carDTO));
    }
}
