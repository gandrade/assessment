package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mytaxi.domainobject.DriverDO_.*;

@Component
public class DriverDOSpecification implements BaseSpecification<DriverDTO, DriverDO>
{

    private CarDOSpecification carDOSpecification;


    public DriverDOSpecification(CarDOSpecification carDOSpecification)
    {
        this.carDOSpecification = carDOSpecification;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Root<DriverDO> path, CriteriaBuilder builder, DriverDTO dtoObject)
    {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(path.get(USERNAME), dtoObject.getUsername() == null ? null : dtoObject.getUsername().toLowerCase()));
        predicates.add(builder.equal(path.get(ONLINE_STATUS), dtoObject.getOnlineStatus()));
        predicates.addAll(carDOSpecification.createPredicates(path.get(carDO), builder, dtoObject.getCarDTO()));
        return predicates;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Path<DriverDO> path, CriteriaBuilder builder, DriverDTO dtoObject)
    {
        return null;
    }

}
