package com.education.projects.users.manager.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {
    @Schema(name = "violations", description = "Violations list")
    private List<Violation> violations = new ArrayList<>();
}
