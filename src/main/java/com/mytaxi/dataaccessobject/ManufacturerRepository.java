package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.ManufacturerDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Database Access Object for manufacturer table.
 * <p/>
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>
{

    Optional<ManufacturerDO> findByNameIgnoreCase(String name);
}
