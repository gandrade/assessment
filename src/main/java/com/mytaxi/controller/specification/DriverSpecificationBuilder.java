package com.mytaxi.controller.specification;

import com.mytaxi.domainobject.DriverDO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class DriverSpecificationBuilder
{
    private final List<SearchCriteria> searchCriterias;


    public DriverSpecificationBuilder()
    {
        searchCriterias = new ArrayList<>();
    }


    public DriverSpecificationBuilder with(String key, String operation, Object value)
    {
        if ("seatCount".equalsIgnoreCase(key)
            || "rating".equalsIgnoreCase(key)
            || "convertible".equalsIgnoreCase(key)
            || "licensePlate".equalsIgnoreCase(key)
            || "engineType".equalsIgnoreCase(key))
        {
            searchCriterias.add(new SearchCriteria("carDO", key, operation, value));
        }
        else
        {
            searchCriterias.add(new SearchCriteria(key, operation, value));
        }
        return this;
    }


    public Specification<DriverDO> build()
    {
        if (searchCriterias.isEmpty())
        {
            return null;
        }

        List<Specification<DriverDO>> specs = new ArrayList<>();
        for (SearchCriteria searchCriteria : searchCriterias)
        {
            specs.add(new DriverSpecification(searchCriteria));
        }

        Specification<DriverDO> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++)
        {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
