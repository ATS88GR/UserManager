package com.education.projects.users.manager.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    @Schema(name = "message", description = "Error message", example = "User with id= wasn't found")
    private String message;
}
