package com.mytaxi.service.manufacturer;

import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.driver.DefaultDriverService;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultManufacturerService implements ManufacturerService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final ManufacturerRepository repository;


    public DefaultManufacturerService(ManufacturerRepository repository)
    {
        this.repository = repository;
    }


    @Override
    public Optional<ManufacturerDO> findByNameIgnoreCase(String name)
    {
        return repository.findByNameIgnoreCase(name);
    }


    @Override
    public ManufacturerDO save(ManufacturerDO manufacturerDO) throws ConstraintsViolationException
    {
        try
        {
            return repository.save(manufacturerDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to manufacturer creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
    }
}
