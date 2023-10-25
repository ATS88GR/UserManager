package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleSearchCriteria {

    @Schema(name = "roleDescr", description = "Role description", example = "system admin")
    @NotBlank(message = "Role description should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String roleDescr;
}
