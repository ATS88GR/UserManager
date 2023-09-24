package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.LevelPage;
import com.education.projects.users.manager.dto.request.LevelSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.exception.LevelNotFoundException;
import com.education.projects.users.manager.mapper.LevelMapper;
import com.education.projects.users.manager.repository.LevelCriteriaRepository;
import com.education.projects.users.manager.repository.LevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@Slf4j
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private LevelCriteriaRepository levelCriteriaRepository;

    /**
     * Gets all levels objects information from database
     *
     * @return The list of the Level objects
     */
    public Collection<LevelDtoResp> getAllLevels() {
        return levelMapper.levelListToLevelDtoList(levelRepository.findAll());
    }

    /**
     * Gets the Level DTO object information from the database by id
     *
     * @param id id of the level object in database
     * @return The Level DTO object from database
     * @throws LevelNotFoundException
     */
    public LevelDtoResp getLevelDtoById(UUID id) throws Exception {
        if (levelRepository.existsById(id))
            return levelMapper.levelToLevelDto(levelRepository.getReferenceById(id));
        throw new LevelNotFoundException(id);
    }

    /**
     * Gets the Level object information from the database by id
     *
     * @param id id of the level object in database
     * @return The Level object from database
     * @throws LevelNotFoundException
     */
    Level getLevelById(UUID id) throws Exception {
        if (levelRepository.existsById(id))
            return levelRepository.getReferenceById(id);
        throw new LevelNotFoundException(id);
    }

    /**
     * Gets paginated list of Level DTO objects from the database
     *
     * @param levelPage           level page parameters
     * @param levelSearchCriteria criteria of level search
     * @return sorted filtered List of Level DTO objects with pagination
     */
    public Page<LevelDtoResp> getSortFilterPaginLevels(LevelPage levelPage,
                                                       LevelSearchCriteria levelSearchCriteria) {
        return levelCriteriaRepository.findAllWithFilters(levelPage, levelSearchCriteria);
    }
}
