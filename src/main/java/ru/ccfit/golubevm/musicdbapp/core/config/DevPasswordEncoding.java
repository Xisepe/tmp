package ru.ccfit.golubevm.musicdbapp.core.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DevPasswordEncoding {
    private final List<String> passwordsToEncode = List.of("test12345", "12345test");
    public DevPasswordEncoding(PasswordEncoder encoder) {
        passwordsToEncode.stream()
                .map(encoder::encode)
                .forEach(System.out::println);
    }
}
