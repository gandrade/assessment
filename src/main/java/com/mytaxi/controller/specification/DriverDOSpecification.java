package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO_;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.DriverDO_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import static com.mytaxi.domainobject.ManufacturerDO_.NAME;

public class DriverDOSpecification
{

    public static Specification<DriverDO> makeDriverDOSpecification(DriverDTO driverDTO)
    {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            CarDTO carDTO = driverDTO.getCarDTO();

            if (driverDTO.getUsername() != null)
            {
                Predicate username = builder.equal(root.get(DriverDO_.USERNAME), driverDTO.getUsername().toLowerCase());
                predicates.add(username);
            }

            if (driverDTO.getOnlineStatus() != null)
            {
                Predicate onlineStatus = builder.equal(root.get(DriverDO_.ONLINE_STATUS), driverDTO.getOnlineStatus());
                predicates.add(onlineStatus);
            }

            if (carDTO.getSeatCount() != null)
            {
                Predicate seatCount = builder.equal(root.get(DriverDO_.carDO).get(CarDO_.SEAT_COUNT), carDTO.getSeatCount());
                predicates.add(seatCount);
            }

            if (carDTO.getLicensePlate() != null)
            {
                Predicate licensePlate = builder.equal(root.get(DriverDO_.carDO).get(CarDO_.LICENSE_PLATE), carDTO.getLicensePlate().toUpperCase());
                predicates.add(licensePlate);
            }

            if (carDTO.getConvertible() != null)
            {
                Predicate convertible = builder.equal(root.get(DriverDO_.carDO).get(CarDO_.CONVERTIBLE), carDTO.getConvertible());
                predicates.add(convertible);
            }

            if (carDTO.getRating() != null)
            {
                Predicate rating = builder.equal(root.get(DriverDO_.carDO).get(CarDO_.RATING), carDTO.getRating());
                predicates.add(rating);
            }

            if (carDTO.getEngineType() != null)
            {
                Predicate engineType = builder.equal(root.get(DriverDO_.carDO).get(CarDO_.ENGINE_TYPE), carDTO.getEngineType());
                predicates.add(engineType);
            }

            if (carDTO.getManufacturerDTO().getName() != null)
            {
                Predicate engineType = builder.equal(root.get(DriverDO_.carDO).get(CarDO_.MANUFACTURER_DO).get(NAME), carDTO.getManufacturerDTO().getName().toUpperCase());
                predicates.add(engineType);
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
