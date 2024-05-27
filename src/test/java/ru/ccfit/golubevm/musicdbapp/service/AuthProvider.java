package ru.ccfit.golubevm.musicdbapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ccfit.golubevm.musicdbapp.core.entity.FixedRole;
import ru.ccfit.golubevm.musicdbapp.core.entity.User;
import ru.ccfit.golubevm.musicdbapp.core.model.LoginInfo;
import ru.ccfit.golubevm.musicdbapp.core.repository.RoleRepository;
import ru.ccfit.golubevm.musicdbapp.core.service.AuthService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthProvider {
    private final AuthService authService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private static final String PSW = "test123456";

    private String getPassword(String password) {
        return encoder.encode(password);
    }

    private User getAdmin() {
        var user = new User();
        user.setEmail("admin@mail.com");
        user.setUsername("testadmin");
        user.setPassword(getPassword(PSW));
        var adminRole = roleRepository.findRoleByName(FixedRole.ROLE_ADMIN.getName()).orElseThrow();
        user.addRole(adminRole);
        return user;
    }
    private User getUser() {
        var user = new User();
        user.setEmail("user@mail.com");
        user.setUsername("testuser");
        user.setPassword(getPassword(PSW));
        return user;
    }
    public LoginInfo provide(FixedRole role) {
        User user;
        switch (role) {
            case ROLE_USER -> user = getUser();
            case ROLE_ADMIN -> user = getAdmin();
            default -> throw new IllegalStateException();
        }
        authService.registerUser(user);
        return authService.loginUser(user.getEmail(), PSW);
    }
}
