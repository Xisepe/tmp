package ru.ccfit.golubevm.musicdbapp.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(description = "Request dto for registration")
@Data
public class RegisterRequest {
    @Email(message = "Must be email format")
    @NotNull
    @NotEmpty
    @Schema(description = "User's email", example = "example@ex.com")
    private final String email;
    @Schema(description = "User's username", example = "amazing1unicorn")
    @Size(max = 64, message = "Username length should be under 64 symbols")
    @Pattern(regexp = "^[a-z0-9]*$", message = "Password has to be in lowercase. Allowed only letters and digits")
    private final String username;
    @Schema(description = "User's password", example = "password123")
    @Size(max = 32, min = 6, message = "Password length at least 6, at most 32")
    @NotNull
    private final String password;
}
