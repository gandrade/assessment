package com.mytaxi.controller;

import com.mytaxi.core.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverCriteriaDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
@Api(tags = {"Driver management"})
public class DriverController
{

    private final DriverService driverService;


    /**
     * Default constructor.
     *
     * @param driverService {@link DriverService}
     */
    public DriverController(DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    @ApiOperation(value = "Return a driver based on its identification.")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverService.find(driverId);
        return DriverMapper.makeDriverDTO(driverDO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a new Driver.")
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        DriverDO driverDOCreated = driverService.create(driverDO);
        return DriverMapper.makeDriverDTO(driverDOCreated);
    }


    @DeleteMapping("/{driverId}")
    @ApiOperation(value = "Deletes a Driver.")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    @ApiOperation(value = "Updates Driver's location.")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @PutMapping("/{driverId}/cars/{carId}/select")
    @ApiOperation(value = "Selects a Driver for a given Car.")
    public DriverDTO selectCar(@PathVariable Long driverId, @PathVariable Long carId)
        throws EntityNotFoundException, ConstraintsViolationException, CarAlreadyInUseException
    {
        DriverDO driverDO = driverService.select(driverId, carId);
        return DriverMapper.makeDriverDTO(driverDO);
    }


    @PutMapping("/{driverId}/cars/{carId}/deselect")
    @ApiOperation(value = "Deselects a Driver for a given Car.")
    public void deselectCar(@PathVariable Long driverId, @PathVariable Long carId) throws EntityNotFoundException
    {
        driverService.deselect(driverId, carId);
    }


    @GetMapping
    @ApiOperation(value = "Returns a list of all drivers based on given criteria.")
    public List<DriverDTO> findDrivers(DriverCriteriaDTO driverCriteriaDTO)
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverCriteriaDTO);
        List<DriverDO> drivers = driverService.findAll(driverDO);
        return DriverMapper.makeDriverDTOList(drivers);
    }


    @InitBinder(value = {"driverCriteriaDTO"})
    public void initBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.initDirectFieldAccess();
    }

}
