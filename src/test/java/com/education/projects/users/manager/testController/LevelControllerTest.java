package com.education.projects.users.manager.testController;

import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.repository.LevelCriteriaRepository;
import com.education.projects.users.manager.repository.LevelRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class LevelControllerTest {
    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }
    @Autowired
    LevelRepository levelRepository;

    @Test
    void getAllLevels() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/levels")
                .then()
                .log().all()
                .statusCode(200)
                .body(".", hasSize(3));
    }
    @Test
    void getSortFilterPaginLevels() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .queryParam("levelDescr", "phd")
                .get("/sortedFilteredLevels")
                .then()
                .log().all()
                .statusCode(200)
                .body("content", hasSize(2));
    }
    @Test
    void getLevelById(){
        UUID levelUUIDTest = levelRepository
                .findAll()
                .stream()
                .filter(level -> level.getLevelDescr().equals("phd"))
                .findFirst()
                .get()
                .getId();

        given()
                .when()
                .pathParams("id", levelUUIDTest)
                .get("/levels/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().body().as(LevelDtoResp.class);

    }
}
