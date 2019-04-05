package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.mytaxi.domainobject.CarDO_.*;

@Component
public class CarDOSpecification implements BaseSpecification<CarDTO, CarDO>
{

    private ManufacturerDOSpecification manufacturerDOSpecification;


    public CarDOSpecification(ManufacturerDOSpecification manufacturerDOSpecification)
    {
        this.manufacturerDOSpecification = manufacturerDOSpecification;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Path<CarDO> path, CriteriaBuilder builder, CarDTO dtoObject)
    {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(builder.equal(path.get(SEAT_COUNT), dtoObject.getSeatCount()));
        predicates.add(builder.equal(path.get(LICENSE_PLATE), dtoObject.getLicensePlate() == null ? null : dtoObject.getLicensePlate().toUpperCase()));
        predicates.add(builder.equal(path.get(CONVERTIBLE), dtoObject.getConvertible()));
        predicates.add(builder.equal(path.get(RATING), dtoObject.getRating()));
        predicates.add(builder.equal(path.get(ENGINE_TYPE), dtoObject.getEngineType()));
        predicates.addAll(manufacturerDOSpecification.createPredicates(path.get(MANUFACTURER_DO), builder, dtoObject.getManufacturerDTO()));
        return predicates;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Root<CarDO> path, CriteriaBuilder builder, CarDTO dtoObject)
    {
        return null;
    }
}
