package com.education.projects.users.manager.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class LevelDtoResp {

    @Schema(name = "id", description = "Level id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private UUID id;

    @Schema (name = "levelDescr", description = "Description of the level", example = "phd")
    private String levelDescr;
}
