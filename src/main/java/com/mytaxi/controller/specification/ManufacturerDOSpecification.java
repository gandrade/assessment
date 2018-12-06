package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.ManufacturerDO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.mytaxi.domainobject.ManufacturerDO_.NAME;

public class ManufacturerDOSpecification {

    public static Specification<ManufacturerDO> makeManufacturerDOSpecification(ManufacturerDTO manufacturerDTO){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.addAll(createPredicates(root, builder, manufacturerDTO));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static List<Predicate> createPredicates(Path<ManufacturerDO> path, CriteriaBuilder builder, ManufacturerDTO manufacturerDTO) {
        List<Predicate> predicates = new ArrayList<>();

        if (manufacturerDTO.getName() != null)
        {
            Predicate name = builder.equal(path.get(NAME), manufacturerDTO.getName().toUpperCase());
            predicates.add(name);
        }
        return predicates;
    }
}
