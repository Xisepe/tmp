package ru.ccfit.golubevm.musicdbapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ccfit.golubevm.musicdbapp.core.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}