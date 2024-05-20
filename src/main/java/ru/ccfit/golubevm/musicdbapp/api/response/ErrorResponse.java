package ru.ccfit.golubevm.musicdbapp.api.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String msg;
    private final Integer statusCode;
    private final String path;
}
