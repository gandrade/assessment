package com.mytaxi.service.driver;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface DriverService
{

    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    DriverDO find(Long driverId) throws EntityNotFoundException;

    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return created driver
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    void delete(Long driverId) throws EntityNotFoundException;

    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude Longitude coordinate
     * @param latitude Latitude coordinate
     * @throws EntityNotFoundException
     */
    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    DriverDO select(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException;

    void deselect(Long driverId, Long carId) throws EntityNotFoundException;

    List<DriverDO> findAll(DriverDO driverDO);

}
