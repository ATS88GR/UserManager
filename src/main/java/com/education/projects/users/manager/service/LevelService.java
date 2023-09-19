package com.education.projects.users.manager.service;

import com.education.projects.users.manager.entity.Level.LevelPage;
import com.education.projects.users.manager.entity.Level.LevelSearchCriteria;
import com.education.projects.users.manager.exception.EmptyException;
import com.education.projects.users.manager.response.dto.LevelDtoResp;
import com.education.projects.users.manager.entity.Level.Level;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.UUID;

public interface LevelService {

    Collection<LevelDtoResp> getAllLevels() throws Exception;

    LevelDtoResp getLevelDtoById(UUID id) throws Exception;

    Page<LevelDtoResp> getSortFilterPaginLevels(LevelPage levelPage,
                                                LevelSearchCriteria levelSearchCriteria) throws Exception;
}
