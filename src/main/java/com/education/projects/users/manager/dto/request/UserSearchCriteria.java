package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchCriteria {

    @Schema (name = "firstName", description = "User firstname", example = "John")
    @NotBlank (message = "FirstName should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String firstName;

    @Schema (name = "lastName", description = "User lastname", example = "Smith")
    @NotBlank (message = "LastName should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String lastName;
}
