package com.mytaxi.service.driver;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    private final CarService carService;


    /**
     * Default constructor.
     * @param driverRepository {@link DriverRepository}
     * @param carService {@link CarService}
     */
    public DefaultDriverService(final DriverRepository driverRepository, CarService carService)
    {
        this.driverRepository = driverRepository;
        this.carService = carService;
    }


    /** {@inheritDoc} */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /** {@inheritDoc} */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /** {@inheritDoc} */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /** {@inheritDoc} */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }

    /** {@inheritDoc} */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    /** {@inheritDoc} */
    @Override
    @Transactional
    public DriverDO select(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException
    {
        if (!carService.existsById(carId))
        {
            throw new EntityNotFoundException("Could not find car with id: " + carId);
        }
        if (driverRepository.existsByIdAndCarDO_IdIsNotNull(driverId))
        {
            throw new ConstraintsViolationException("Driver " + driverId + " already has a car selected.");
        }
        CarDO carDO = carService.findAvailable(carId);
        DriverDO driverDO = findOnlineDriver(driverId);
        carDO.setDriverDO(driverDO);
        return driverDO;
    }

    /** {@inheritDoc} */
    @Override
    @Transactional
    public void deselect(Long driverId, Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carService.deselect(carId, driverId);
        carDO.setDriverDO(null);
    }

    /** {@inheritDoc} */
    @Override
    public List<DriverDO> findAll(Specification<DriverDO> spec)
    {
        return driverRepository.findAll(spec);
    }


    private DriverDO findOnlineDriver(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findByIdAndOnlineStatus(driverId, OnlineStatus.ONLINE)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
