package ru.ccfit.golubevm.musicdbapp.api.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {
    private final Integer id;
    private final String username;
    private final String email;
    private final List<String> roles;
}
