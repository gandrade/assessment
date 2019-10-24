package com.mytaxi.service.manufacturer;

import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

public interface ManufacturerService
{

    /**
     * Find manufacturer by name ignoring case.
     *
     * @param name Manufacture's name.
     * @return Manufacturer
     * @throws EntityNotFoundException When manufacturer name was not found.
     */
    ManufacturerDO findByNameIgnoreCase(String name) throws EntityNotFoundException;
}

