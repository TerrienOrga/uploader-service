package com.ote.row.model;

import com.ote.framework.model.Parameter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RowParameter extends Parameter<Row> {

    private String nodeLeft;
    private String nodeRight;
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

    public Specification<Row> getFilter() {

        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (nodeLeft != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("nodeLeft")), nodeLeft.toUpperCase()));
            }

            if (nodeRight != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("nodeRight")), nodeRight.toUpperCase()));
            }

            if (distance != null) {
                predicates.add(criteriaBuilder.equal(root.get("distance"), distance));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


}
