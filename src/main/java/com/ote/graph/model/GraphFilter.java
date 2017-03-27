package com.ote.graph.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GraphFilter extends Filter<GraphElement> {

    private String source;
    private String destination;
    private Double distance;

    public Sort getSort() {

        Sort.Order orderByPrimaryKeyAsc = new Sort.Order(Sort.Direction.ASC, "id");

        if (sortingBy == null) {
            return new Sort(orderByPrimaryKeyAsc);
        }

        Sort.Order orderByPropertyAndDirection = new Sort.Order(sortingDirection, sortingBy);

        if ("id".equalsIgnoreCase(sortingBy)) {
            return new Sort(orderByPropertyAndDirection);
        }

        return new Sort(orderByPropertyAndDirection, orderByPrimaryKeyAsc);
    }

    public boolean isFiltered() {

        return (this.source != null || this.destination != null || this.distance != null);
    }

    public Specification<GraphElement> getFilter() {

        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (isFiltered()) {
                if (source != null) {
                    predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("source")), source.toUpperCase()));
                }

                if (destination != null) {
                    predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("destination")), destination.toUpperCase()));
                }

                if (distance != null) {
                    predicates.add(criteriaBuilder.equal(root.get("distance"), distance));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}