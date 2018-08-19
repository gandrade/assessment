package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends CrudRepository<CarDO, Long> {

    @Query("SELECT car FROM CarDO car WHERE car.driverDO IS NULL AND id =:id")
    Optional<CarDO> findByIdAndDriverDO(@Param("id") Long carId);

    Optional<CarDO> findByIdAndDriverDOIsNull(Long carId);

}
