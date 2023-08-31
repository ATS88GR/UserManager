package com.education.projects.users.manager.service;

import com.education.projects.users.manager.response.dto.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;

import java.util.Collection;
import java.util.UUID;

public interface LevelService {

    Collection<LevelDtoResp> getAllLevels() throws Exception;
    LevelDtoResp getLevelDtoById(UUID id) throws Exception;
    Level getLevelById(UUID id) throws Exception;
}
