package com.education.projects.users.manager.controller;

import com.education.projects.users.manager.response.dto.RoleDtoResp;
import com.education.projects.users.manager.service.RoleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@Validated
@Slf4j
@Tag(name = "Roles API")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Operation(summary = "Gets information about all roles from database",
            description = "Returns collection of role objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/roles")
    public ResponseEntity<Collection<RoleDtoResp>> getRoles() throws Exception {
        log.info("Get all roles info");
        return new ResponseEntity<>(roleServiceImpl.getAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "Gets role by id",
            description = "Returns id role information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found - The role was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDtoResp> getRoleById(@PathVariable("id") @NotNull @Min(1) UUID id)
            throws Exception {
        log.info("Gets role with id = {}", id);
        return new ResponseEntity<>(roleServiceImpl.getRoleDtoById(id), HttpStatus.OK);
    }
}
