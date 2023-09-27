package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPage extends CommonPage {
    public UserPage() {
        super("lastName");
    }

    @NotBlank
    @Schema(name = "sortBy",
            description = "Sorting by firstName/lastName/roleId/levelId",
            example = "lastName")
    private String sortBy = "lastName";


}
