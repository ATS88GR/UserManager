package com.education.projects.users.manager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleDtoResp {
    @Schema(name = "id", description = "Role id", example = "1")
    private Integer id;

    @Schema (name = "roleDescr", description = "Description of the role", example = "administrator")
    private String roleDescr;
}
