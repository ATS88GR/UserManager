package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class UserPage extends CommonPage {
    UserPage() {
        super("lastName");
    }

    @NotBlank
    @Schema(name = "sortBy",
            description = "Sorting by firstName/lastName/roleId/levelId",
            example = "lastName")
    private String sortBy = "lastName";


}
