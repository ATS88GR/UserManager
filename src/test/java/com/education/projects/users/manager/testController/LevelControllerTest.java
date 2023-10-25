package com.education.projects.users.manager.testController;

import com.education.projects.users.manager.dto.request.AuthenticationRequest;
import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.repository.LevelRepository;
import com.education.projects.users.manager.repository.RoleRepository;
import com.education.projects.users.manager.repository.UserRepository;
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
public class LevelControllerTest {
    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15");

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private final UserDtoReq userForSecurity = new UserDtoReq(
            "Sec",
            "Secure",
            "Security",
            "123@gmail.com",
            "+375255432112",
            null,
            null);

    private String token;

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

        userRepository.deleteAll();

        userForSecurity.setRoleId(getRoleByDescr("system admin").getId());
        userForSecurity.setLevelId(getLevelByDescr("phd").getId());

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(userForSecurity)
                .post("auth/register")
                .then()
                .log().all()
                .statusCode(200);

        token = given()
                .contentType(ContentType.JSON)
                .when()
                .body(new AuthenticationRequest("123@gmail.com", "Security"))
                .post("auth/authenticate")
                .jsonPath()
                .getString("access_token");
    }

    @Test
    void getAllLevels() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization","Bearer "+ token)
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
                .header("Authorization","Bearer "+ token)
                .queryParam("levelDescr", "phd")
                .get("/sortedFilteredLevels")
                .then()
                .log().all()
                .statusCode(200)
                .body("content", hasSize(2));
    }

    @Test
    void getLevelById() {
        UUID levelUUIDTest = levelRepository
                .findAll()
                .stream()
                .filter(level -> level.getLevelDescr().equals("phd"))
                .findFirst()
                .get()
                .getId();

        given()
                .when()
                .header("Authorization","Bearer "+ token)
                .pathParams("id", levelUUIDTest)
                .get("/levels/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().body().as(LevelDtoResp.class);

    }
    private Role getRoleByDescr(String descr) {
        return roleRepository.findAll()
                .stream()
                .filter(role -> role.getRoleDescr().equals(descr))
                .findFirst()
                .get();
    }

    private Level getLevelByDescr(String descr) {
        return levelRepository.findAll()
                .stream()
                .filter(level -> level.getLevelDescr().equals(descr))
                .findFirst()
                .get();
    }
}
