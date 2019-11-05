package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import com.mytaxi.service.manufacturer.ManufacturerService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final CarRepository carRepository;

    private final ManufacturerService manufacturerService;


    /**
     * Default constructor.
     * @param carRepository {@link CarRepository}.
     * @param manufacturerService {@link ManufacturerService}.
     */
    public DefaultCarService(final CarRepository carRepository, final ManufacturerService manufacturerService)
    {
        this.carRepository = carRepository;
        this.manufacturerService = manufacturerService;
    }


    /** {@inheritDoc */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }


    /** {@inheritDoc */
    @Override
    public CarDO findAvailable(Long carId) throws CarAlreadyInUseException
    {
        return carRepository.findByIdAndDriverDOIsNull(carId)
            .orElseThrow(() -> new CarAlreadyInUseException("Car " + carId + " already in use."));
    }


    /** {@inheritDoc */
    @Override
    public CarDO deselect(Long carId, Long driverId) throws EntityNotFoundException
    {
        return carRepository.findByIdAndDriverDO_Id(carId, driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find car with id: " + carId + " for the driver: " + driverId));
    }


    /** {@inheritDoc */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException, EntityNotFoundException
    {
        try
        {
            ManufacturerDO manufacturerDO = manufacturerService.findByNameIgnoreCase(carDO.getManufacturerDO().getName());
            carDO.setManufacturerDO(manufacturerDO);
            return carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.error("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }


    /** {@inheritDoc */
    @Override
    public void update(long carId, CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException
    {
        try
        {
            CarDO car = this.find(carId);
            car.setEngineType(carDO.getEngineType());
            car.setSeatCount(carDO.getSeatCount());
            car.setConvertible(carDO.getConvertible());
            car.setLicensePlate(carDO.getLicensePlate());
            car.setRating(carDO.getRating());
            ManufacturerDO manufacturerDO = manufacturerService.findByNameIgnoreCase(carDO.getManufacturerDO().getName());
            car.setManufacturerDO(manufacturerDO);
            car.setDriverDO(carDO.getDriverDO());
            carRepository.save(car);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.error("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }


    /** {@inheritDoc */
    @Override
    public void delete(Long carId) throws EntityNotFoundException
    {
        try
        {
            carRepository.deleteById(carId);
        }
        catch (EmptyResultDataAccessException e)
        {
            LOG.error("Couldn't exclude car " + carId + ".", e);
            throw new EntityNotFoundException("Couldn't exclude car " + carId + ".");
        }
    }


    /** {@inheritDoc */
    @Override
    public boolean existsById(Long carId)
    {
        return carRepository.existsById(carId);
    }


    /** {@inheritDoc */
    @Override
    public List<CarDO> findAll()
    {
        return (List<CarDO>) carRepository.findAll();
    }
}
