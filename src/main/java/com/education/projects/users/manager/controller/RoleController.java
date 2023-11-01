package com.education.projects.users.manager.controller;

import com.education.projects.users.manager.dto.request.RolePage;
import com.education.projects.users.manager.dto.request.RoleSearchCriteria;
import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.service.RoleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
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
            @ApiResponse(responseCode = "404", description = "The role list is empty"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/roles")
    public ResponseEntity<Collection<RoleDtoResp>> getRoles() {
        log.info("Get all roles info");
        return new ResponseEntity<>(roleServiceImpl.getAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "Gets sorted and filtered information about roles from database",
            description = "Returns collection of role objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "The role list is empty"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/sortedFilteredRoles")
    public ResponseEntity<Page<RoleDtoResp>> getSortFilterRolesWithPagination(RolePage rolePage,
                                                                              RoleSearchCriteria roleSearchCriteria) {
        log.info("Get common sorted and filtered role info");
        return new ResponseEntity<>(roleServiceImpl.getSortFilterPaginRoles(rolePage, roleSearchCriteria),
                HttpStatus.OK);
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
    public ResponseEntity<RoleDtoResp> getRoleById(@PathVariable("id") UUID id)
            throws Exception {
        log.info("Gets role with id {}", id);
        return new ResponseEntity<>(roleServiceImpl.getRoleDtoById(id), HttpStatus.OK);
    }
}
