package ru.ccfit.golubevm.musicdbapp.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.ccfit.golubevm.musicdbapp.config.IntegrationTestConfig;
import ru.ccfit.golubevm.musicdbapp.core.repository.*;
import ru.ccfit.golubevm.musicdbapp.core.repository.projection.ArtistInfo;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class SqlQueriesTest extends IntegrationTestConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private PlaylistRepository playlistRepository;


    @Test
    @Sql("/json_user_test.sql")
    void json_query_test() {
        var ans = userRepository.findUsersWithName("Maxim");
        Assertions.assertEquals(ans.size(), 1);
    }

    @Test
    @Sql("/group_by_inner_join_having_test.sql")
    void group_by_inner_join_having_test() {
        var ans = artistRepository.findArtistsWithMoreAlbumsThan(4);
        assertThat(ans, Matchers.hasSize(3));
    }

    @Test
    @Sql("/group_by_inner_join_having_test.sql")
    void left_join_group_by() {
        var ans = artistRepository.getAllArtistsWithTotalAlbums();
        assertThat(ans, Matchers.hasSize(5));
        var nums = ans.stream()
                .map(ArtistInfo::getTotalAlbums)
                .sorted()
                .toList();
        assertThat(nums, Matchers.equalTo(List.of(3L,3L,5L,5L,7L)));
    }

    @Test
    @Sql("/cte_test.sql")
    void cte_test() {
        var ans = userRepository.findUsersWithPlaylists();
        assertThat(ans, Matchers.hasSize(2));
    }

    @Test
    @Sql("/filter_sort_test.sql")
    void filter_sort_test() {
        var res = songRepository.findSongsByDurationBetweenOrderByTitle(55,70);
        assertThat(res, Matchers.hasSize(3));
    }

    @Test
    @Sql("/limit_offset_test.sql")
    void limit_offset_test() {
        var res = songRepository.findTopFiveMostPopularSongs();
        assertThat(res, Matchers.hasSize(2));
    }

    @Test
    @Sql("/procedure_test.sql")
    void procedure_test() {
        var res = albumRepository.getTotalDuration(1);
        assertThat(res, Matchers.equalTo(20));
    }
    @Test
    @Sql("/subquery_test.sql")
    void subquery_test() {
        var res = userRepository.findUsersWithPlaylistsContainingSongWithDuration(50);
        assertThat(res, Matchers.hasSize(1));
    }

    @Test
    @Sql("/lateral_join_test.sql")
    void lateral_join_test() {
        var res = artistRepository.getArtistsWithLatestAlbum();
        assertThat(res.get(0).getAlbumId(), Matchers.equalTo(2));
    }
    @Test
    @Sql("/upsert_test.sql")
    void upsert_test() {
        userRepository.upsert(1,"test2@test.test","test","updated");
        var user = userRepository.findUserByEmail("test2@test.test").orElseThrow();
        assertThat(user.getUsername(), Matchers.equalTo("updated"));
    }
}
