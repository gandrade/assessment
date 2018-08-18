package com.mytaxi.service.manufacturer;

import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;

import java.util.Optional;

public interface ManufacturerService {

    Optional<ManufacturerDO> findByNameIgnoreCase(String name);

    ManufacturerDO save(ManufacturerDO manufacturerDO) throws ConstraintsViolationException;
}

