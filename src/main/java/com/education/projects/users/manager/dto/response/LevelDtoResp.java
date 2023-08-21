package com.education.projects.users.manager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LevelDtoResp {

    @Schema(name = "id", description = "Level id", example = "1")
    private Integer id;

    @Schema (name = "levelDescr", description = "Description of the level", example = "professor")
    private String levelDescr;
}
