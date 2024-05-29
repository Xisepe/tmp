package ru.ccfit.golubevm.musicdbapp.core.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ccfit.golubevm.musicdbapp.core.repository.projection.UserWithPlaylistTitleAndSongTitle;
import ru.ccfit.golubevm.musicdbapp.core.repository.projection.UserWithPlaylists;
import ru.ccfit.golubevm.musicdbapp.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(@NotNull String email);

    Boolean existsByEmail(@NotNull String email);

    Boolean existsByUsername(@NotNull String username);
    @Query("SELECT u FROM User u WHERE EXISTS (SELECT 1 FROM Playlist p JOIN p.songs s WHERE p.user = u AND s.duration > :duration)")
    List<User> findUsersWithPlaylistsContainingSongWithDuration(@Param("duration") int duration);

    @Query(value = "WITH UserPlaylists AS (" +
            "    SELECT DISTINCT user_id " +
            "    FROM Playlist" +
            ") " +
            "SELECT id, username FROM Users u " +
            "JOIN UserPlaylists up ON u.id = up.user_id", nativeQuery = true)
    List<UserWithPlaylists> findUsersWithPlaylists();

    @Query(value = "select * from users where metadata ->> 'name' = ?", nativeQuery = true)
    List<User> findUsersWithName(@NotNull String name);

    @Query(value = """
insert into users (id, email, password, username)
values (:id, :email, :password, :username)
on conflict (id)
do update set 
username = excluded.username,
password = excluded.password,
email = excluded.email;
""", nativeQuery = true)
    @Modifying
    @Transactional
    int upsert(@Param("id") Integer id, @Param("email") String email, @Param("password") String password, @Param("username") String username);

}