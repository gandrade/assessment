package com.mytaxi.service.driver;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDO assign(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException;

    DriverDO unassign(Long driverId, Long carId) throws EntityNotFoundException;

    List<DriverDO> findAll(Specification<DriverDO> spec);
}
