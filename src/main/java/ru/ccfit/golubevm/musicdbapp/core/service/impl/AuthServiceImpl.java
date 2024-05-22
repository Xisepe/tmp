package ru.ccfit.golubevm.musicdbapp.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ccfit.golubevm.musicdbapp.core.entity.FixedRole;
import ru.ccfit.golubevm.musicdbapp.core.entity.User;
import ru.ccfit.golubevm.musicdbapp.core.model.LoginInfo;
import ru.ccfit.golubevm.musicdbapp.core.model.UserDetailsImpl;
import ru.ccfit.golubevm.musicdbapp.core.repository.RoleRepository;
import ru.ccfit.golubevm.musicdbapp.core.repository.UserRepository;
import ru.ccfit.golubevm.musicdbapp.core.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Override
    public String registerUser(User user) {
        var userRole = roleRepository.findRoleByName(FixedRole.ROLE_USER.getName())
                .orElseThrow();
        user.addRole(userRole);
        userRepository.save(user);
        return "User successfully registered";
    }

    @Override
    public LoginInfo loginUser(String email, String password) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        var userDetails = (UserDetailsImpl) auth.getPrincipal();
        var jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return new LoginInfo(
                userDetails,
                jwtCookie.toString()
        );
    }

    @Override
    public String getLogoutCookie() {
        return jwtUtils.getCleanJwtCookie().toString();
    }

}
