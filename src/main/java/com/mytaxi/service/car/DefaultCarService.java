package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import com.mytaxi.service.manufacturer.ManufacturerService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final CarRepository carRepository;

    private final ManufacturerService manufacturerService;


    public DefaultCarService(final CarRepository carRepository, final ManufacturerService manufacturerService)
    {
        this.carRepository = carRepository;
        this.manufacturerService = manufacturerService;
    }


    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }


    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException, EntityNotFoundException
    {
        try
        {
            carDO.setManufacturerDO(manufacturerService.findByNameIgnoreCase(carDO.getManufacturerDO().getName()));
            return carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.error("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }

    @Override
    public void delete(Long carId)
    {
        carRepository.deleteById(carId);
    }


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
            car.setManufacturerDO(manufacturerService.findByNameIgnoreCase(carDO.getManufacturerDO().getName()));
            carRepository.save(car);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.error("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }
}
