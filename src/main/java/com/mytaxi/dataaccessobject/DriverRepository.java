package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    DriverDO findByIdAndCarDO(Long driverId, Long carId);

    Optional<DriverDO> findByIdAndOnlineStatus(Long driverId, OnlineStatus online);
}
