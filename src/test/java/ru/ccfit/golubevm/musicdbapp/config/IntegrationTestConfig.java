package ru.ccfit.golubevm.musicdbapp.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Configuration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class IntegrationTestConfig {
    @Autowired
    private PostgreSQLContainer<?> container;
    @LocalServerPort
    private int port;
    @BeforeAll
    protected void setup() {
        container.start();
    }

    @AfterAll
    protected void shutdown() {
        container.stop();
    }

    @BeforeEach
    protected void prepare() {
        RestAssured.baseURI = "http://localhost:" + port;
    }
}
