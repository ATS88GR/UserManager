package com.education.projects.users.manager.entity.Level;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LevelSearchCriteria {

    @Schema (name = "levelDescr", description = "Level description", example = "phd")
    @NotBlank (message = "Level description should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String levelDescr;
}
