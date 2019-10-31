package com.mytaxi.specification;

import com.mytaxi.domainobject.DriverDO;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.mytaxi.domainobject.DriverDO_.*;

@Component
public class DriverDOSpecificationExecutor implements DefaultSpecificationExecutor<DriverDO>
{

    private final CarDOSpecificationExecutor carDOSpecification;


    public DriverDOSpecificationExecutor(CarDOSpecificationExecutor carDOSpecification)
    {
        this.carDOSpecification = carDOSpecification;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Root<DriverDO> path, CriteriaBuilder builder, DriverDO doObject)
    {
        if (doObject == null) {
            return Collections.emptySet();
        }
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(builder.equal(path.get(USERNAME), doObject.getUsername() == null ? null : doObject.getUsername().toLowerCase()));
        predicates.add(builder.equal(path.get(ONLINE_STATUS), doObject.getOnlineStatus()));
        Collection<? extends Predicate> carPredicates = carDOSpecification.createPredicates(path.get(carDO), builder, doObject.getCarDO());
        predicates.addAll(carPredicates);
        return predicates;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Path<DriverDO> path, CriteriaBuilder builder, DriverDO doObject)
    {
        return null;
    }

}
