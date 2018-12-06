package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.DriverDO_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class DriverDOSpecification
{

    public static Specification<DriverDO> makeDriverDOSpecification(DriverDTO driverDTO)
    {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.addAll(createPredicates(root, builder, driverDTO));
            predicates.addAll(CarDOSpecification.createPredicates(root.get(DriverDO_.carDO), builder, driverDTO.getCarDTO()));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private static List<Predicate> createPredicates(Path<DriverDO> root, CriteriaBuilder builder, DriverDTO driverDTO) {
        List<Predicate> predicates = new ArrayList<>();
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
        return predicates;
    }
}
