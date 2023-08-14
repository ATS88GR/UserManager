package com.education.projects.users.manager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDtoResp {

    @Schema(name = "id", description = "User id", example = "1")
    private Integer Id;

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

    @Schema (name = "roleId", description = "User role", example = "2")
    private Integer roleId;

    @Schema (name = "levelId", description = "User level", example = "3")
    private Integer levelId;

    @Schema (name = "createdAt", description = "User creation date", example = "2017-05-14T10:34")
    private LocalDateTime createdAt;

    @Schema (name = "modificationAt", description = "User modification date", example = "2017-05-14T10:34")
    private LocalDateTime modificationAt;
}
