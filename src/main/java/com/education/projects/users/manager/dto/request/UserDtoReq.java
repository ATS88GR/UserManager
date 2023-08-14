package com.education.projects.users.manager.dto.request;

import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDtoReq {

    @Schema (name = "firstName", description = "User firstname", example = "John")
    @NotBlank (message = "FirstName should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String firstName;

    @Schema (name = "lastName", description = "User lastname", example = "Smith")
    @NotBlank (message = "LastName should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String lastName;

    @Schema (name = "password", description = "User password", example = "Gib5!?jEs#")
    @NotBlank (message = "Password should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String password;

    @Schema (name = "email", description = "User email", example = "abcdefg@gmail.com")
    @NotBlank (message = "Email should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String email;

    @Schema (name = "phone", description = "User phone number", example = "+375334455667")
    @NotBlank (message = "Phone should not be blank")
    @Size(min = 7, max = 20, message = "20 characters max")
    private String phone;

    @Schema (name = "roleId", description = "User role", example = "2")
    @NotNull (message = "RoleId should not be empty")
    @Positive (message = "RoleId should be positive")
    @Digits(integer = 2, fraction = 0, message = "integer number of not more than 2 characters")
    private Integer roleId;

    @Schema (name = "levelId", description = "User level", example = "3")
    @NotNull (message = "LevelId should not be empty")
    @Positive (message = "LevelId should be positive")
    @Digits(integer = 2, fraction = 0, message = "integer number of not more than 2 characters")
    private Integer levelId;
}
