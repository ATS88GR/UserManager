package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoReq {
    @Schema(name = "firstName", description = "User firstname", example = "John")
    @NotBlank(message = "FirstName should not be blank")
    @Size(min = 1, max = 32, message = "1 character min, 32 characters max")
    private String firstName;

    @Schema(name = "lastName", description = "User lastname", example = "Smith")
    @NotBlank(message = "LastName should not be blank")
    @Size(min = 1, max = 32, message = "1 character min, 32 characters max")
    private String lastName;

    @Schema(name = "password", description = "User password", example = "Gib5!?jEs#")
    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, max = 32, message = "8 characters min, 32 characters max")
    private String password;

    @Schema(name = "email", description = "User email", example = "abcdefg@gmail.com")
    @NotBlank(message = "Email should not be blank")
    @Size(min = 8, max = 32, message = "8 characters min, 32 characters max")
    private String email;

    @Schema(name = "phone", description = "User phone number", example = "+375334455667")
    @NotBlank(message = "Phone should not be blank")
    @Size(min = 7, max = 20, message = "7 character min, 20 characters max")
    private String phone;

    @Schema(name = "roleId", description = "User role", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @NotNull(message = "RoleId should not be empty")
    private UUID roleId;

    @Schema(name = "levelId", description = "User level", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @NotNull(message = "LevelId should not be empty")
    private UUID levelId;
}
