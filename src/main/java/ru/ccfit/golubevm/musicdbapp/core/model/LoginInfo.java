package ru.ccfit.golubevm.musicdbapp.core.model;

import lombok.Data;

import java.util.List;

@Data
public class LoginInfo {
    private final UserDetailsImpl userDetails;
    private final String cookie;
}
