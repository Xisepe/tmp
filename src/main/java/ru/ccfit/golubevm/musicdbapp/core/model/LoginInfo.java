package ru.ccfit.golubevm.musicdbapp.core.model;

import lombok.Data;

@Data
public class LoginInfo {
    private final UserDetailsImpl userDetails;
    private final String cookie;
}
