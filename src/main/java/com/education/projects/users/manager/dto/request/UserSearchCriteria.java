package com.education.projects.users.manager.dto.request;

import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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

    @Schema (name = "role", description = "User role")
    private Role role;

    @Schema (name = "level", description = "User level")
    private Level level;
}
