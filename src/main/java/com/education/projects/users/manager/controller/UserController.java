package com.education.projects.users.manager.controller;

import com.education.projects.users.manager.request.dto.UserDtoReq;
import com.education.projects.users.manager.response.dto.UserDtoResp;
import com.education.projects.users.manager.entity.User.UserPage;
import com.education.projects.users.manager.entity.User.UserSearchCriteria;
import com.education.projects.users.manager.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@Validated
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
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PostMapping("/users")  //url
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
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDtoResp> updateUser(
            @Valid @RequestBody UserDtoReq userDtoReq,
            @PathVariable("id") @NotNull UUID id)
            throws Exception {
        log.info("Update user with id {}, update user info {}", id, userDtoReq);
        return new ResponseEntity<>(userServiceImpl.updateUser(userDtoReq, id), HttpStatus.OK);
    }

    @Operation(summary = "Gets information about all users from database",
            description = "Returns collection of user objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/users")
    public ResponseEntity<Collection<UserDtoResp>> getUsers() throws Exception {
        log.info("Get all user info");
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Gets sorted and filtered information about users from database",
            description = "Returns collection of user objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/sortedFilteredUsers")
    public ResponseEntity<Page<UserDtoResp>> getSortFilterUsersWithPagination(UserPage userPage,
                                                                      UserSearchCriteria userSearchCriteria)
            throws Exception {
        log.info("Get common sorted and filtered user info");
        return new ResponseEntity<>(userServiceImpl.getSortFilterPaginUsers(userPage, userSearchCriteria),
                HttpStatus.OK);
    }

    @Operation(summary = "Gets user by id",
            description = "Returns id user information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found - The user was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDtoResp> getUserById(
            @PathVariable("id") @NotNull UUID id)
            throws Exception {
        log.info("Gets user with id {}", id);
        return new ResponseEntity<>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Deletes user by id",
            description = "Removes the row with id from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found - The user was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(
            @PathVariable("id") @NotNull UUID id)
            throws Exception {
        log.info("Deletes user with id {}", id);
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>("The user deleted", HttpStatus.OK);
    }
}
