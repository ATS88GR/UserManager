package com.education.projects.users.manager.repository;

import com.education.projects.users.manager.dto.request.LevelPage;
import com.education.projects.users.manager.dto.request.LevelSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.mapper.LevelMapper;
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
public class LevelCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    LevelMapper levelMapper;

    public LevelCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<LevelDtoResp> findAllWithFilters(LevelPage levelPage,
                                                 LevelSearchCriteria levelSearchCriteria) {
        CriteriaQuery<Level> criteriaQuery = criteriaBuilder.createQuery(Level.class);
        Root<Level> levelRoot = criteriaQuery.from(Level.class);
        Predicate predicate = getPredicate(levelSearchCriteria, levelRoot);
        criteriaQuery.where(predicate);

        setOrder(levelPage, criteriaQuery, levelRoot);

        TypedQuery<Level> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(levelPage.getPageNumber() * levelPage.getPageSize());
        typedQuery.setMaxResults(levelPage.getPageSize());

        Pageable pageable = getPageable(levelPage);

        long levelsCount = getLevelsCount();

        return (new PageImpl<>(
                levelMapper.levelListToLevelDtoList(typedQuery.getResultList()),
                pageable,
                levelsCount));
    }

    private long getLevelsCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Level> countRoot = countQuery.from(Level.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(LevelPage levelPage) {
        Sort sort = Sort.by(levelPage.getSortDirection(), levelPage.getSortBy());
        return PageRequest.of(levelPage.getPageNumber(), levelPage.getPageSize(), sort);
    }

    private void setOrder(LevelPage levelPage,
                          CriteriaQuery<Level> criteriaQuery,
                          Root<Level> levelRoot) {
        if (levelPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(levelRoot.get(levelPage.getSortBy())));
        } else criteriaQuery.orderBy(criteriaBuilder.desc(levelRoot.get(levelPage.getSortBy())));
    }

    private Predicate getPredicate(LevelSearchCriteria levelSearchCriteria,
                                   Root<Level> levelRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(levelSearchCriteria.getLevelDescr()))
            predicates.add(criteriaBuilder.like(levelRoot.get("levelDescr"),
                    "%" + levelSearchCriteria.getLevelDescr() + "%"));
        return criteriaBuilder.and(predicates.toArray((new Predicate[0])));
    }
}
