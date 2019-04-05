package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{
    Optional<CarDO> findByIdAndDriverDOIsNull(Long carId);

    Optional<CarDO> findByIdAndDriverDO_Id(Long carId, Long driverId);
}
