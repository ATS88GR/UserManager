package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Schema(name = "email", description = "User email", example = "abcdefg@gmail.com")
    @NotBlank(message = "Email should not be blank")
    @Size(min = 8, max = 32, message = "8 characters min, 32 characters max")
    private String email;

    @Schema(name = "password", description = "User password", example = "Gib5!?jEs#")
    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, max = 32, message = "8 characters min, 32 characters max")
    String password;
}
