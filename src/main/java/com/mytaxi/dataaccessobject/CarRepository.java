package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDO, Long>
{
    Optional<CarDO> findByIdAndDriverDOIsNull(Long carId);
}
