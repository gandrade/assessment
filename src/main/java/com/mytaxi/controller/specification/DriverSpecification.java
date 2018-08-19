package com.mytaxi.controller.specification;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.OnlineStatus;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DriverSpecification implements Specification<DriverDO>
{
    private SearchCriteria criteria;


    public DriverSpecification(SearchCriteria criteria)
    {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(
        Root<DriverDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {
        if (criteria.getValue() == null)
        {
            return null;
        }

        if (criteria.getJoinObject() != null)
        {
            Predicate predicate;
            Join<Object, Object> carDO = root.join(criteria.getJoinObject());
            root.fetch(criteria.getJoinObject());
            if (carDO.get(criteria.getKey()).getJavaType() == EngineType.class)
            {
                String value = (String) criteria.getValue();
                predicate = criteriaBuilder.equal(carDO.get(criteria.getKey()), EngineType.valueOf(value.toUpperCase()));
            }
            else
            {
                predicate = criteriaBuilder.equal(carDO.get(criteria.getKey()), criteria.getValue());
            }
            return predicate;
        }

        if (root.get(criteria.getKey()).getJavaType() == String.class)
        {
            return criteriaBuilder.like(
                root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        else if (root.get(criteria.getKey()).getJavaType() == OnlineStatus.class)
        {
            String value = (String) criteria.getValue();
            return criteriaBuilder.equal(
                root.get(criteria.getKey()), OnlineStatus.valueOf(value.toUpperCase()));
        }
        else
        {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }


}
