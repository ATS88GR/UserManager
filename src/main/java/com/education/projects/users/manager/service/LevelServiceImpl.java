package com.education.projects.users.manager.service;

import com.education.projects.users.manager.response.dto.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.mapper.LevelMapper;
import com.education.projects.users.manager.repository.LevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Gets all levels objects information from database
     *
     * @return The list of the Level objects
     */
    public Collection<LevelDtoResp> getAllLevels() throws Exception{
        try {
            return levelMapper.levelListToLevelDtoList(levelRepository.findAll());
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Gets the Level object information from the database by id
     *
     * @param id id of the level object in database
     * @return The Level object from database
     * @throws Exception
     */
    public LevelDtoResp getLevelDtoById(UUID id) throws Exception {
        try {
            if (levelRepository.existsById(id))
                return levelMapper.levelToLevelDto(levelRepository.getReferenceById(id));
            else {
                Exception e = new Exception("The level wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public Level getLevelById(UUID id) throws Exception {
        try {
            if (levelRepository.existsById(id))
                return levelRepository.getReferenceById(id);
            else {
                Exception e = new Exception("The level wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
