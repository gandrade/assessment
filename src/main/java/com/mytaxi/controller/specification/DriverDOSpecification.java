package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.mytaxi.domainobject.DriverDO_.*;

@Component
public class DriverDOSpecification implements BaseSpecification<DriverDTO, DriverDO>
{

    private final CarDOSpecification carDOSpecification;


    public DriverDOSpecification(CarDOSpecification carDOSpecification)
    {
        this.carDOSpecification = carDOSpecification;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Root<DriverDO> path, CriteriaBuilder builder, DriverDTO dtoObject)
    {
        if (dtoObject == null) {
            return Collections.emptySet();
        }
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(builder.equal(path.get(USERNAME), dtoObject.getUsername() == null ? null : dtoObject.getUsername().toLowerCase()));
        predicates.add(builder.equal(path.get(ONLINE_STATUS), dtoObject.getOnlineStatus()));
        Collection<? extends Predicate> carPredicates = carDOSpecification.createPredicates(path.get(carDO), builder, dtoObject.getCarDTO());
        predicates.addAll(carPredicates);
        return predicates;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Path<DriverDO> path, CriteriaBuilder builder, DriverDTO dtoObject)
    {
        return null;
    }

}
