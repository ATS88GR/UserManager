package com.education.projects.users.manager.repository;

import com.education.projects.users.manager.entity.Level.Level;
import com.education.projects.users.manager.entity.Level.LevelPage;
import com.education.projects.users.manager.entity.Level.LevelSearchCriteria;
import com.education.projects.users.manager.mapper.LevelMapper;
import com.education.projects.users.manager.response.dto.LevelDtoResp;
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

        //long levelsCount = getLevelsCount(predicate);
        long levelsCount = 10;

        return (new PageImpl<>(
                levelMapper.levelListToLevelDtoList(typedQuery.getResultList()),
                pageable,
                levelsCount));
    }

    private long getLevelsCount(Predicate predicate) {
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
