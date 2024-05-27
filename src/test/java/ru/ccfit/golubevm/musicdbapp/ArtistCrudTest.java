package ru.ccfit.golubevm.musicdbapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ccfit.golubevm.musicdbapp.api.request.CreateArtistRequest;
import ru.ccfit.golubevm.musicdbapp.config.AuthIntegrationTestConfig;
import ru.ccfit.golubevm.musicdbapp.core.entity.FixedRole;
import ru.ccfit.golubevm.musicdbapp.core.repository.ArtistRepository;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class ArtistCrudTest extends AuthIntegrationTestConfig {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void shouldCreateArtist(){
        var artist = new CreateArtistRequest(
                "test_artist",
                null,
                "rock"
        );
        given(FixedRole.ROLE_ADMIN)
                .body(List.of(artist))
                .when()
                .post("/api/artist/")
                .then()
                .statusCode(200);
    }

}
