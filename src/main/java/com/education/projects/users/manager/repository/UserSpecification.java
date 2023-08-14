package com.education.projects.users.manager.repository;
import com.education.projects.users.manager.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {
    private SearchCriteria criteria;

    public UserSpecification(SearchCriteria searchCriteria) {
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("eq")) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        } else return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
    }
}
