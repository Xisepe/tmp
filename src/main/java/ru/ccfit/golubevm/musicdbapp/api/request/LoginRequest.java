package ru.ccfit.golubevm.musicdbapp.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email
    @NotNull
    @NotEmpty
    private final String email;
    @Size(max = 32, min = 6)
    @NotNull
    private final String password;
}
