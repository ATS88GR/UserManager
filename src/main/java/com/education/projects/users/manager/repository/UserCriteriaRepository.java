package com.education.projects.users.manager.repository;

import com.education.projects.users.manager.dto.request.UserPage;
import com.education.projects.users.manager.dto.request.UserSearchCriteria;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.User;
import com.education.projects.users.manager.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    UserMapper userMapper;

    public UserCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<UserDtoResp> findAllWithFilters(UserPage userPage,
                                               UserSearchCriteria userSearchCriteria){
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = getPredicate(userSearchCriteria, userRoot);
        criteriaQuery.where(predicate);

        setOrder(userPage, criteriaQuery, userRoot);

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(userPage.getPageNumber() * userPage.getPageSize());
        typedQuery.setMaxResults(userPage.getPageSize());

        Pageable pageable = getPageable(userPage);

        long usersCount = getUsersCount();

        return (new PageImpl<>(
                userMapper.userListToUserDtoList(typedQuery.getResultList()),
                pageable,
                usersCount));
    }

    private long getUsersCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(UserPage userPage) {
        Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
        return PageRequest.of(userPage.getPageNumber(),userPage.getPageSize(), sort);
    }

    private void setOrder(UserPage userPage,
                          CriteriaQuery<User> criteriaQuery,
                          Root<User> userRoot) {
        if(userPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(userPage.getSortBy())));
        } else criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(userPage.getSortBy())));
    }

    private Predicate getPredicate(UserSearchCriteria userSearchCriteria,
                                   Root<User> userRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(userSearchCriteria.getFirstName()))
            predicates.add(criteriaBuilder.like(userRoot.get("firstName"),
                    "%" + userSearchCriteria.getFirstName() + "%"));
        if(Objects.nonNull(userSearchCriteria.getLastName()))
            predicates.add(criteriaBuilder.like(userRoot.get("lastName"),
                    "%" + userSearchCriteria.getLastName() + "%"));
        return criteriaBuilder.and(predicates.toArray((new Predicate[0])));
    }
}
