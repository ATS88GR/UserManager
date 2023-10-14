package com.education.projects.users.manager.testController;

import com.education.projects.users.manager.dto.response.RoleDtoResp;
import com.education.projects.users.manager.repository.RoleRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class RoleControllerTest {
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
    RoleRepository roleRepository;

    @Test
    void getAllRoles() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/roles")
                .then()
                .log().all()
                .statusCode(200)
                .body(".", hasSize(4));
    }
    @Test
    void getSortFilterPaginRoles() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .queryParam("roleDescr", "user")
                .get("/sortedFilteredRoles")
                .then()
                .log().all()
                .statusCode(200)
                .body("content", hasSize(1));
    }
    @Test
    void getRolesById(){
        UUID roleUUIDTest = roleRepository
                .findAll()
                .stream()
                .filter(role -> role.getRoleDescr().equals("user"))
                .findFirst()
                .get()
                .getId();

        given()
                .when()
                .pathParams("id", roleUUIDTest)
                .get("/roles/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().body().as(RoleDtoResp.class);

    }
}
