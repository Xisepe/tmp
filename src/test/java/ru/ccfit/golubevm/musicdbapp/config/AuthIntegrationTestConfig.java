package ru.ccfit.golubevm.musicdbapp.config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ccfit.golubevm.musicdbapp.core.entity.FixedRole;
import ru.ccfit.golubevm.musicdbapp.core.model.LoginInfo;
import ru.ccfit.golubevm.musicdbapp.service.AuthProvider;


public abstract class AuthIntegrationTestConfig extends IntegrationTestConfig {
    @Autowired
    private AuthProvider authProvider;
    protected LoginInfo user;
    protected LoginInfo admin;
    @BeforeAll
    @Override
    public void setup() {
        super.setup();
        user = authProvider.provide(FixedRole.ROLE_USER);
        admin = authProvider.provide(FixedRole.ROLE_ADMIN);
    }
    protected RequestSpecification given(FixedRole role) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(
                        "Cookie",
                        switch (role) {
                            case ROLE_ADMIN -> admin.getCookie();
                            case ROLE_USER -> user.getCookie();
                        }
                        );
    }
}
