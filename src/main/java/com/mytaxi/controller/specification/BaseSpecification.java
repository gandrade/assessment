package com.mytaxi.controller.specification;

import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseSpecification<T, E>
{

    default Specification<E> makeSpecification(T dtoObject)
    {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>(this.createPredicates(root, builder, dtoObject));
            Predicate[] filteredPredicates = predicates.stream()
                .filter(predicate -> predicate instanceof ComparisonPredicate &&
                    ((LiteralExpression) ((ComparisonPredicate) predicate).getRightHandOperand()).getLiteral() != null)
                .toArray(Predicate[]::new);
            return builder.and(filteredPredicates);
        };
    }

    Collection<? extends Predicate> createPredicates(Root<E> path, CriteriaBuilder builder, T dtoObject);

    Collection<? extends Predicate> createPredicates(Path<E> path, CriteriaBuilder builder, T dtoObject);
}
