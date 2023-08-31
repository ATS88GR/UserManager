package com.education.projects.users.manager.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

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

    @Schema (name = "roleId", description = "User role", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @NotNull (message = "RoleId should not be empty")
    private UUID roleId;

    @Schema (name = "levelId", description = "User level", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @NotNull (message = "LevelId should not be empty")
    private UUID levelId;
}