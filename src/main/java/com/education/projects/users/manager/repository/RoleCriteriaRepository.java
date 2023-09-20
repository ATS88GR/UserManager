package com.education.projects.users.manager.repository;

import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.dto.request.RolePage;
import com.education.projects.users.manager.dto.request.RoleSearchCriteria;
import com.education.projects.users.manager.mapper.RoleMapper;
import com.education.projects.users.manager.dto.response.RoleDtoResp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    RoleMapper roleMapper;

    public RoleCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<RoleDtoResp> findAllWithFilters(RolePage rolePage,
                                                 RoleSearchCriteria roleSearchCriteria) {
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        Predicate predicate = getPredicate(roleSearchCriteria, roleRoot);
        criteriaQuery.where(predicate);

        setOrder(rolePage, criteriaQuery, roleRoot);

        TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(rolePage.getPageNumber() * rolePage.getPageSize());
        typedQuery.setMaxResults(rolePage.getPageSize());

        Pageable pageable = getPageable(rolePage);

        long rolesCount = 10;

        return (new PageImpl<>(
                roleMapper.roleListToRoleDtoList(typedQuery.getResultList()),
                pageable,
                rolesCount));
    }

    private long getRolesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Role> countRoot = countQuery.from(Role.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(RolePage rolePage) {
        Sort sort = Sort.by(rolePage.getSortDirection(), rolePage.getSortBy());
        return PageRequest.of(rolePage.getPageNumber(), rolePage.getPageSize(), sort);
    }

    private void setOrder(RolePage rolePage,
                          CriteriaQuery<Role> criteriaQuery,
                          Root<Role> roleRoot) {
        if (rolePage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(roleRoot.get(rolePage.getSortBy())));
        } else criteriaQuery.orderBy(criteriaBuilder.desc(roleRoot.get(rolePage.getSortBy())));
    }

    private Predicate getPredicate(RoleSearchCriteria roleSearchCriteria,
                                   Root<Role> roleRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(roleSearchCriteria.getRoleDescr()))
            predicates.add(criteriaBuilder.like(roleRoot.get("roleDescr"),
                    "%" + roleSearchCriteria.getRoleDescr() + "%"));
        return criteriaBuilder.and(predicates.toArray((new Predicate[0])));
    }
}
