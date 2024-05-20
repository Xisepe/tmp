package ru.ccfit.golubevm.musicdbapp.core.service.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ccfit.golubevm.musicdbapp.api.request.RegisterRequest;
import ru.ccfit.golubevm.musicdbapp.api.response.UserInfoResponse;
import ru.ccfit.golubevm.musicdbapp.core.entity.User;
import ru.ccfit.golubevm.musicdbapp.core.exception.EmailAlreadyInUseException;
import ru.ccfit.golubevm.musicdbapp.core.exception.UsernameAlreadyTakenException;
import ru.ccfit.golubevm.musicdbapp.core.model.UserDetailsImpl;
import ru.ccfit.golubevm.musicdbapp.core.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AuthMapper {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public abstract User toEntity(RegisterRequest registerRequest);

    @BeforeMapping
    public void validateRegisterForExistence(RegisterRequest registerRequest, @MappingTarget User user) {
        var email = registerRequest.getEmail();
        var username = registerRequest.getUsername();
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }
        if (username != null && userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyTakenException(username);
        }
    }

    @AfterMapping
    public void encodePassword(RegisterRequest registerRequest, @MappingTarget User user) {
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );
    }
    @Mapping(target = "roles", source = "authorities")
    public abstract UserInfoResponse toUserInfoResponse(UserDetailsImpl loginInfo);
    public List<String> toStringRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

}
