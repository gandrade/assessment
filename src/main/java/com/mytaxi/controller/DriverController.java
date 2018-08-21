package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.controller.specification.DriverDOSpecification;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;
    private final CarService carService;


    @Autowired
    public DriverController(final DriverService driverService, final CarService carService)
    {
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }

    @PutMapping("/{driverId}/cars/{carId}/assign")
    public DriverDTO assignCar(@PathVariable Long driverId, @PathVariable Long carId) throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException
    {
        return DriverMapper.makeDriverDTO(driverService.assign(driverId, carId));
    }


    @PutMapping("/{driverId}/cars/{carId}/deselect")
    public DriverDTO unassingCar(@PathVariable Long driverId, @PathVariable Long carId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.unassign(carId, driverId));
    }

    @GetMapping
    public List<DriverDTO> findDrivers(DriverDTO driverDTO)
    {
        Specification<DriverDO> spec = DriverDOSpecification.makeDriverDOSpecification(driverDTO);
        return DriverMapper.makeDriverDTOList(driverService.findAll(spec));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
