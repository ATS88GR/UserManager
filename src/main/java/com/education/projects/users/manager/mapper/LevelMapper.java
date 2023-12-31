package com.education.projects.users.manager.mapper;

import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    LevelDtoResp levelToLevelDto(Level level);
    List<LevelDtoResp> levelListToLevelDtoList(List<Level> levels);
}
