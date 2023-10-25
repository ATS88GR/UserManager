package com.education.projects.users.manager.testController;

import com.education.projects.users.manager.dto.request.AuthenticationRequest;
import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.response.UserDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.entity.Role;
import com.education.projects.users.manager.entity.User;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserControllerTest {
    @LocalServerPort
    private Integer port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15");

    @Autowired
    UserRepository userRepository;
    @Autowired
    LevelRepository levelRepository;
    @Autowired
    RoleRepository roleRepository;

    private final User user = new User(
            null,
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            null,
            null,
            null,
            null);
    private final User user2 = new User(
            null,
            "Michael",
            "Spark",
            "kjb^7jh!23,",
            "poiuyr@gmail.com",
            "+375298765432",
            null,
            null,
            null,
            null);

    private final UserDtoReq userDtoReq = new UserDtoReq(
            "John",
            "Smith",
            "Gib5!?jEs#",
            "abcdefg@gmail.com",
            "+375334455667",
            null,
            null);

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

        user.setId(null);
        user.setCreatedAt(null);
        user.setModificationAt(null);
        user.setRole(null);
        user.setLevel(null);

        user2.setId(null);
        user2.setCreatedAt(null);
        user2.setModificationAt(null);
        user2.setRole(null);
        user2.setLevel(null);
    }

    @Test
    void testPostgresIsRunning() {
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void getUsers() {
        user.setRole(getRoleByDescr("moderator"));
        user.setLevel(getLevelByDescr("amateur"));

        user2.setRole(getRoleByDescr("system admin"));
        user2.setLevel(getLevelByDescr("phd"));

        userRepository.save(user);
        userRepository.save(user2);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .body(".", hasSize(3));
    }

    @Test
    void createUser() {
        userDtoReq.setRoleId(getRoleByDescr("moderator").getId());
        userDtoReq.setLevelId(getLevelByDescr("amateur").getId());

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(userDtoReq)
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON)
                .extract().body().as(UserDtoResp.class);
    }

    @Test
    void updateUser() {
        userDtoReq.setRoleId(getRoleByDescr("system admin").getId());
        userDtoReq.setLevelId(getLevelByDescr("phd").getId());

        user.setRole(getRoleByDescr("moderator"));
        user.setLevel(getLevelByDescr("amateur"));

        UUID id = userRepository.save(user).getId();

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().body(userDtoReq)
                .pathParams("id", id)
                .put("/users/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().body().as(UserDtoResp.class);
    }

    @Test
    void getUserById() {
        user.setRole(getRoleByDescr("moderator"));
        user.setLevel(getLevelByDescr("amateur"));

        user2.setRole(getRoleByDescr("system admin"));
        user2.setLevel(getLevelByDescr("phd"));

        userRepository.save(user);
        UUID idTest = userRepository.save(user2).getId();

        given()
                .when()
                .header("Authorization", "Bearer " + token)
                .pathParams("id", idTest)
                .get("/users/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().body().as(UserDtoResp.class);
    }

    @Test
    void deleteUserById() {
        user.setRole(getRoleByDescr("moderator"));
        user.setLevel(getLevelByDescr("amateur"));

        user2.setRole(getRoleByDescr("system admin"));
        user2.setLevel(getLevelByDescr("phd"));

        userRepository.save(user);
        UUID idTest = userRepository.save(user2).getId();

        given()
                .when()
                .header("Authorization", "Bearer " + token)
                .body(userDtoReq)
                .pathParams("id", idTest)
                .delete("/users/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .contentType(ContentType.TEXT);
    }

    @Test
    void deleteUserByIdOnFail() {
        user.setRole(getRoleByDescr("moderator"));
        user.setLevel(getLevelByDescr("amateur"));

        user2.setRole(getRoleByDescr("system admin"));
        user2.setLevel(getLevelByDescr("phd"));

        userRepository.save(user);
        UUID idTest = UUID.fromString("4fc4200c-0fd8-409e-9d61-5862d306b4a4");


        given()
                .when()
                .header("Authorization", "Bearer " + token)
                .body(userDtoReq)
                .pathParams("id", idTest)
                .delete("/users/{id}")
                .then()
                .log().all()
                .statusCode(404)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    void getSortFilterUsersWithPagination() {
        user.setRole(getRoleByDescr("moderator"));
        user.setLevel(getLevelByDescr("amateur"));

        user2.setRole(getRoleByDescr("system admin"));
        user2.setLevel(getLevelByDescr("phd"));

        userRepository.save(user);
        userRepository.save(user2);

        given()
                .contentType(ContentType.JSON)
                .when()
                .header("Authorization", "Bearer " + token)
                .queryParam("sortBy", "firstName")
                .queryParam("firstName", "John")
                .queryParam("lastName", "Smith")
                .get("/sortedFilteredUsers")
                .then()
                .log().all()
                .statusCode(200)
                .body("content", hasSize(1));
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
