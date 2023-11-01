package com.education.projects.users.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    @Schema(name = "accessToken", description = "Access Token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2NTQzMjFAZ21haWwuY29tIiwiaWF0IjoxNjk4MjAyNjU1LCJleHAiOjE2OTgyMDQwOTV9.8_DqKG0WnoI6M_vetjykhohQepp_ktlaRe6ei_aE0Qc")
    private String accessToken;
}
