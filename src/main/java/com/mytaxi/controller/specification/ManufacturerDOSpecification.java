package com.mytaxi.controller.specification;

import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.ManufacturerDO;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mytaxi.domainobject.ManufacturerDO_.NAME;

@Component
public class ManufacturerDOSpecification implements BaseSpecification<ManufacturerDTO, ManufacturerDO>
{

    public List<Predicate> createPredicates(Path<ManufacturerDO> path, CriteriaBuilder builder, ManufacturerDTO manufacturerDTO)
    {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(path.get(NAME), manufacturerDTO.getName() == null ? null : manufacturerDTO.getName().toUpperCase()));
        return predicates;
    }


    @Override
    public Collection<? extends Predicate> createPredicates(Root<ManufacturerDO> path, CriteriaBuilder builder, ManufacturerDTO dtoObject)
    {
        return null;
    }
}
