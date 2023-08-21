package com.education.projects.users.manager.controller;

import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.service.LevelServiceImpl;
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

@RestController
@Validated
@Slf4j
@Tag(name = "Levels API")
public class LevelController {
    @Autowired
    private LevelServiceImpl levelServiceImpl;

    @Operation(summary = "Gets information about all levels from database",
            description = "Returns collection of level objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/levels")
    public ResponseEntity<Collection<LevelDtoResp>> getLevels() throws Exception {
        log.info("Get all levels info");
        return new ResponseEntity <> (levelServiceImpl.getAllLevels(), HttpStatus.OK);
    }

    @Operation(summary = "Gets level by id",
            description = "Returns id level information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found - The level was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/levels/{id}")
    public ResponseEntity<LevelDtoResp> getLevelById(@PathVariable("id") @NotNull @Min(1) Integer id)
            throws Exception{
        log.info("Gets level with id = {}", id);
        return new ResponseEntity <> (levelServiceImpl.getLevelDtoById(id), HttpStatus.OK);
    }
}
