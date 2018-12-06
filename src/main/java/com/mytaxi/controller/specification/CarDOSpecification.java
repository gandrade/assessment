package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarDO_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class CarDOSpecification {

    public static Specification<CarDO> makeCarDOSpecification(CarDTO carDTO){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.addAll(createPredicates(root, builder, carDTO));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static List<Predicate> createPredicates(Path<CarDO> path, CriteriaBuilder builder, CarDTO carDTO) {
        List<Predicate> predicates = new ArrayList<>();
        if (carDTO.getSeatCount() != null)
        {
            Predicate seatCount = builder.equal(path.get(CarDO_.SEAT_COUNT), carDTO.getSeatCount());
            predicates.add(seatCount);
        }

        if (carDTO.getLicensePlate() != null)
        {
            Predicate licensePlate = builder.equal(path.get(CarDO_.LICENSE_PLATE), carDTO.getLicensePlate().toUpperCase());
            predicates.add(licensePlate);
        }

        if (carDTO.getConvertible() != null)
        {
            Predicate convertible = builder.equal(path.get(CarDO_.CONVERTIBLE), carDTO.getConvertible());
            predicates.add(convertible);
        }

        if (carDTO.getRating() != null)
        {
            Predicate rating = builder.equal(path.get(CarDO_.RATING), carDTO.getRating());
            predicates.add(rating);
        }

        if (carDTO.getEngineType() != null)
        {
            Predicate engineType = builder.equal(path.get(CarDO_.ENGINE_TYPE), carDTO.getEngineType());
            predicates.add(engineType);
        }

        predicates.addAll(ManufacturerDOSpecification.createPredicates(path.get(CarDO_.MANUFACTURER_DO), builder, carDTO.getManufacturerDTO()));
        return predicates;
    }
}
