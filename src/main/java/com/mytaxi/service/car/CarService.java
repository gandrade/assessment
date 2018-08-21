package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface CarService
{
    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO findAvailable(Long carId) throws CarAlreadyInUseException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException, EntityNotFoundException;

    void update(long carId, CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    boolean existsById(Long carId);

    List<CarDO> findAll();
}
