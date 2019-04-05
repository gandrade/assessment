package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    Optional<DriverDO> findByIdAndOnlineStatus(Long driverId, OnlineStatus online);

    // @Query("SELECT CASE WHEN COUNT(driver) > 0 THEN true ELSE false END FROM DriverDO driver INNER JOIN driver.carDO as carDO WHERE carDO IS NOT NULL AND driver.id=:id ")
    boolean existsByIdAndCarDO_IdIsNotNull(Long driverId);
}
