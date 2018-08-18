package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import com.mytaxi.service.manufacturer.ManufacturerService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarService implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final CarRepository carRepository;

    private final ManufacturerService manufacturerService;


    public DefaultCarService(final CarRepository carRepository, final ManufacturerService manufacturerService) {
        this.carRepository = carRepository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        try {
            ManufacturerDO manufacturerDO = updateManufacturer(carDO);
            carDO.setManufacturerDO(manufacturerDO);
            return carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }


    //FIXME Improve method name
    private ManufacturerDO updateManufacturer(CarDO carDO) throws ConstraintsViolationException
    {
        return manufacturerService.findByNameIgnoreCase(carDO.getManufacturerDO().getName())
                        .orElse(manufacturerService.save(carDO.getManufacturerDO()));
    }


    @Override
    public void delete(Long carId) throws EntityNotFoundException {
        carRepository.deleteById(carId);
    }

    @Override
    public void update(long carId, CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException {
        if (carRepository.existsById(carId)) {
            carDO.setId(carId);
        }
        carDO.setManufacturerDO(updateManufacturer(carDO));
        carRepository.save(carDO);
    }
}
