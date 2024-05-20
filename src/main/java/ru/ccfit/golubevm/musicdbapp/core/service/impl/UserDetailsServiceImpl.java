package ru.ccfit.golubevm.musicdbapp.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ccfit.golubevm.musicdbapp.core.model.UserDetailsImpl;
import ru.ccfit.golubevm.musicdbapp.core.repository.UserRepository;
@Primary
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User: "+ username+ "Not Found!"));
        return UserDetailsImpl.of(user);
    }
}
