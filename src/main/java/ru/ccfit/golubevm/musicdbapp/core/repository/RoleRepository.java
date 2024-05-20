package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ccfit.golubevm.musicdbapp.core.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(String name);
}