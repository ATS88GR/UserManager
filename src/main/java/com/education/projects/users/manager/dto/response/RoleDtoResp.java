package com.education.projects.users.manager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoleDtoResp {
    @Schema(name = "id", description = "Role id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private UUID id;

    @Schema (name = "roleDescr", description = "Description of the role", example = "administrator")
    private String roleDescr;
}
