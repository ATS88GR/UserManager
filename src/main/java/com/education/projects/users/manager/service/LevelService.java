package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;

import java.util.Collection;

public interface LevelService {

    Collection<LevelDtoResp> getAllLevels() throws Exception;
    LevelDtoResp getLevelDtoById(Integer id) throws Exception;
    Level getLevelById(Integer id) throws Exception;
}
