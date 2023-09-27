package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.LevelPage;
import com.education.projects.users.manager.dto.request.LevelSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.UUID;

public interface LevelService {

    Collection<LevelDtoResp> getAllLevels() throws Exception;

    LevelDtoResp getLevelDtoById(UUID id) throws Exception;

    Page<LevelDtoResp> getSortFilterPaginLevels(LevelPage levelPage,
                                                LevelSearchCriteria levelSearchCriteria) throws Exception;
}
