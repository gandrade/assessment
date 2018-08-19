package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService
{
    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException, EntityNotFoundException;

    void update(long carId, CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;
}
