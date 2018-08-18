package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.ManufacturerDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long> {

    boolean existsByName(String name);

    Optional<ManufacturerDO> findByNameIgnoreCase(String name);
}
