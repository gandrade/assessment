package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDO, Long> {
}
