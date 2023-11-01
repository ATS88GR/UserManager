package com.education.projects.users.manager.controller;

import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.request.UserPage;
import com.education.projects.users.manager.dto.request.UserSearchCriteria;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@Slf4j
@Tag(name = "Users API")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Operation(summary = "Creates new row in database with user information",
            description = "Returns created user information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden, you do not have permission"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PostMapping("/users")
    public ResponseEntity<UserDtoResp> createUser(@Valid @RequestBody UserDtoReq userDtoReq)
            throws Exception {
        log.info("Create user: {}", userDtoReq);
        return new ResponseEntity<>(userServiceImpl.createUser(userDtoReq), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates user information by id",
            description = "Returns updated user information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The user was not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden, you do not have permission"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDtoResp> updateUser(
            @Valid @RequestBody UserDtoReq userDtoReq,
            @PathVariable("id") UUID id)
            throws Exception {
        log.info("Update user with id {}, update user info {}", id, userDtoReq);
        return new ResponseEntity<>(userServiceImpl.updateUser(userDtoReq, id), HttpStatus.OK);
    }

    @Operation(summary = "Gets information about all users from database",
            description = "Returns collection of user objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden, you do not have permission"),
            @ApiResponse(responseCode = "404", description = "User list is empty"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/users")
    public ResponseEntity<Collection<UserDtoResp>> getUsers() {
        log.info("Get all user info");
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Gets sorted and filtered information about users from database",
            description = "Returns collection of user objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden, you do not have permission"),
            @ApiResponse(responseCode = "404", description = "User list is empty"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/sortedFilteredUsers")
    public ResponseEntity<Page<UserDtoResp>> getSortFilterUsersWithPagination(
            @Valid UserPage userPage,
            @Valid UserSearchCriteria userSearchCriteria) {
        log.info("Get common sorted and filtered user info");
        return new ResponseEntity<>(userServiceImpl.getSortFilterPaginUsers(userPage, userSearchCriteria),
                HttpStatus.OK);
    }

    @Operation(summary = "Gets user by id",
            description = "Returns id user information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden, you do not have permission"),
            @ApiResponse(responseCode = "404", description = "Not found - The user was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDtoResp> getUserById(
            @PathVariable("id") UUID id) {
        log.info("Gets user with id {}", id);
        return new ResponseEntity<>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Deletes user by id",
            description = "Removes the row with id from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden, you do not have permission"),
            @ApiResponse(responseCode = "404", description = "Not found - The user was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(
            @PathVariable("id") UUID id)
            throws Exception {
        log.info("Deletes user with id {}", id);
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>("The user deleted", HttpStatus.OK);
    }
}
