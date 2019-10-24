package com.mytaxi.service.manufacturer;

import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.DefaultDriverService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultManufacturerService implements ManufacturerService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final ManufacturerRepository repository;


    /**
     * Default constructor.
     *
     * @param repository {@link ManufacturerRepository}
     */
    public DefaultManufacturerService(ManufacturerRepository repository)
    {
        this.repository = repository;
    }


    /** {@inheritDoc} */
    @Override
    public ManufacturerDO findByNameIgnoreCase(String name) throws EntityNotFoundException
    {
        return repository.findByNameIgnoreCase(name)
            .orElseThrow(() -> new EntityNotFoundException("Manufacturer '" + name + "' already exists"));
    }
}
