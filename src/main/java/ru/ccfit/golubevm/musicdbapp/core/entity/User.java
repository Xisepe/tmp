package ru.ccfit.golubevm.musicdbapp.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.parameters.P;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users", indexes = {
        @Index(name = "users_username_key", columnList = "username", unique = true),
        @Index(name = "idx_users_email", columnList = "email"),
        @Index(name = "idx_users_username", columnList = "username"),
        @Index(name = "idx_users_created_on", columnList = "created_on"),
        @Index(name = "idx_users_metadata", columnList = "metadata")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 60)
    @NotNull
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Size(max = 64)
    @Column(name = "username", length = 64)
    private String username;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Playlist> playlists = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Album> albums = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    public void addRole(Role role) {
        if (roles == null) {
            roles = new LinkedHashSet<>();
        }
        roles.add(role);
    }
}