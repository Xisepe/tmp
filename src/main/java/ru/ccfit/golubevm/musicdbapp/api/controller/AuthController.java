package ru.ccfit.golubevm.musicdbapp.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ccfit.golubevm.musicdbapp.api.request.LoginRequest;
import ru.ccfit.golubevm.musicdbapp.api.request.RegisterRequest;
import ru.ccfit.golubevm.musicdbapp.api.response.ErrorResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.MessageResponse;
import ru.ccfit.golubevm.musicdbapp.api.response.UserInfoResponse;
import ru.ccfit.golubevm.musicdbapp.core.service.AuthService;
import ru.ccfit.golubevm.musicdbapp.core.service.mapper.AuthMapper;

@Tag(name = "Auth", description = "This controller is used to auth")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthMapper authMapper;
    private final AuthService authService;

    @Operation(summary = "Register new user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(schema = @Schema(implementation = MessageResponse.class),
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data, or user with such credentials already exists",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @PostMapping("/register")
    public MessageResponse register(
            @Parameter(description = "Registration request")
            @RequestBody @Valid RegisterRequest request
    ) {
        var user = authMapper.toEntity(request);
        return new MessageResponse(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        var loginInfo = authService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        var response = authMapper.toUserInfoResponse(loginInfo.getUserDetails());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, loginInfo.getCookie())
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, authService.getLogoutCookie())
                .body(new MessageResponse("You've been successfully logged out"));
    }
}
