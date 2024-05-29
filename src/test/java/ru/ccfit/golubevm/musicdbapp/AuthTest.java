package ru.ccfit.golubevm.musicdbapp;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ccfit.golubevm.musicdbapp.api.request.LoginRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.RegisterRequest;
import ru.ccfit.golubevm.musicdbapp.config.IntegrationTestConfig;
import ru.ccfit.golubevm.musicdbapp.core.entity.User;
import ru.ccfit.golubevm.musicdbapp.core.repository.UserRepository;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

class AuthTest extends IntegrationTestConfig {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @BeforeEach
    @Override
    public void prepare() {
        super.prepare();
        repository.deleteAll();
    }

    @Test
    void checkProtectedRoutes() {
        when().get("/api/")
                .then()
                .statusCode(401);
    }

    @Test
    void shouldRegisterUser() {
        var user = new RegisterRequest(
                "maxim.golubeff2014@gmail.com",
                "xisepe",
                "test12345"
        );
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .body("message", equalTo("User successfully registered"));
    }

    @Test
    void shouldFailRegisterUser_EmailAlreadyTaken() {
        var user = new User();
        user.setEmail("maxim.golubeff2014@gmail.com");
        user.setPassword(encoder.encode("test12345"));
        repository.save(user);

        var request = new RegisterRequest(
                "maxim.golubeff2014@gmail.com",
                "xisepe",
                "test12345"
        );

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(400)
                .body("msg", equalTo("Email already in use: " + request.getEmail()));

    }

    @Test
    void shouldFailRegisterUser_UsernameAlreadyTaken() {
        var user = new User();
        user.setEmail("maxim.golubeff2014@gmail.com");
        user.setUsername("xisepe");
        user.setPassword(encoder.encode("test12345"));
        repository.save(user);

        var request = new RegisterRequest(
                "maxim.golubeff2015@gmail.com",
                "xisepe",
                "test12345"
        );

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(400)
                .body("msg", equalTo("Username already taken: " + request.getUsername()));
    }

    @Test
    void shouldLogin() {
        var user = new User();
        user.setEmail("maxim.golubeff2014@gmail.com");
        user.setUsername("xisepe");
        user.setPassword(encoder.encode("test12345"));
        repository.save(user);

        var request = new LoginRequest(
                "maxim.golubeff2014@gmail.com",
                "test12345"
        );
        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("email", equalTo("maxim.golubeff2014@gmail.com"))
                .header(HttpHeaders.SET_COOKIE, notNullValue());
    }
}
