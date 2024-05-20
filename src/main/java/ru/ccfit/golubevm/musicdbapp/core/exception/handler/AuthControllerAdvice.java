package ru.ccfit.golubevm.musicdbapp.core.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ccfit.golubevm.musicdbapp.api.response.ErrorResponse;
import ru.ccfit.golubevm.musicdbapp.core.exception.AuthException;

@RestControllerAdvice
public class AuthControllerAdvice {
    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<ErrorResponse> handleEmailAlreadyInUseException(
            AuthException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getServletPath())
                );
    }
}
