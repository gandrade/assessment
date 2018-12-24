package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.ManufacturerDO;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for manufacturer table.
 * <p/>
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>
{

    Optional<ManufacturerDO> findByNameIgnoreCase(String name);
}
