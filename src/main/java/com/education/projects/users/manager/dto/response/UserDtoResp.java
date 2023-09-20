package com.education.projects.users.manager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserDtoResp {

    @Schema(name = "id", description = "User id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private UUID Id;

    @Schema (name = "firstName", description = "User firstname", example = "John")
    private String firstName;

    @Schema (name = "lastName", description = "User lastname", example = "Smith")
    private String lastName;

    @Schema (name = "password", description = "User password", example = "Gib5!?jEs#")
    private String password;

    @Schema (name = "email", description = "User email", example = "abcdefg@gmail.com")
    private String email;

    @Schema (name = "phone", description = "User phone number", example = "+375334455667")
    private String phone;

    @Schema (name = "roleId", description = "User role", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private RoleDtoResp roleDtoResp;

    @Schema (name = "levelId", description = "User level", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    private LevelDtoResp levelDtoResp;

    @Schema (name = "createdAt", description = "User creation date", example = "2017-05-14T10:34")
    private Timestamp createdAt;

    @Schema (name = "modificationAt", description = "User modification date", example = "2017-05-14T10:34")
    private Timestamp modificationAt;
}
