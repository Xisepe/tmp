package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ccfit.golubevm.musicdbapp.UserWithPlaylists;
import ru.ccfit.golubevm.musicdbapp.core.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE EXISTS (SELECT 1 FROM Playlist p JOIN p.songs s WHERE p.user = u AND s.duration > :duration)")
    List<User> findUsersWithPlaylistsContainingSongWithDuration(@Param("duration") int duration);

    @Query(value = "WITH UserPlaylists AS (" +
            "    SELECT DISTINCT user_id " +
            "    FROM Playlist" +
            ") " +
            "SELECT id, username FROM Users u " +
            "JOIN UserPlaylists up ON u.id = up.user_id", nativeQuery = true)
    List<UserWithPlaylists> findUsersWithPlaylists();


}