package com.mytaxi.specification;

import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.hibernate.query.criteria.internal.predicate.ComparisonPredicate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Default base implementation for creating predicates following Specification implementation.
 * @param <T> Entity object.
 */
interface DefaultSpecificationExecutor<T>
{

    /**
     * Creates a Specification object given an object.
     *
     * @param doObject Data Access Object object.
     * @return Specification containing all predicates for querying objects.
     */
    default Specification<T> makeSpecification(T doObject)
    {
        return (Specification<T>) (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>(this.createPredicates(root, builder, doObject));
            Predicate[] filteredPredicates = predicates.stream()
                .filter(predicate -> predicate instanceof ComparisonPredicate)
                .filter(predicate -> {
                    Expression rightHandOperand = ((ComparisonPredicate) predicate).getRightHandOperand();
                    Object literal = ((LiteralExpression) rightHandOperand).getLiteral();
                    return literal != null;
                })
                .toArray(Predicate[]::new);
            return builder.and(filteredPredicates);
        };
    }

    Collection<? extends Predicate> createPredicates(Root<T> path, CriteriaBuilder builder, T doObject);

    Collection<? extends Predicate> createPredicates(Path<T> path, CriteriaBuilder builder, T doObject);
}
