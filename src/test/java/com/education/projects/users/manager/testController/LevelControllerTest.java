package com.education.projects.users.manager.testController;

import com.education.projects.users.manager.dto.request.LevelPage;
import com.education.projects.users.manager.dto.request.LevelSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.repository.LevelCriteriaRepository;
import com.education.projects.users.manager.repository.LevelRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Page;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    @Mock
    LevelCriteriaRepository levelCriteriaRepository;

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
        Page<LevelDtoResp> pageLevelDtoRespExp = mock(Page.class);
        LevelPage levelPage = new LevelPage();
        LevelSearchCriteria levelSearchCriteria = new LevelSearchCriteria("phd");

        when(levelCriteriaRepository.findAllWithFilters(levelPage, levelSearchCriteria)).thenReturn(pageLevelDtoRespExp);

        given()
                .contentType(ContentType.JSON)
                .when()
                //.queryParam("levelPage", levelPage)
                //.queryParam("levelSearchCriteria", levelSearchCriteria)
                .get("/sortedFilteredLevels")
                .then()
                .log().all()
                .statusCode(200)
                .body("content", hasSize(3));
    }

}
