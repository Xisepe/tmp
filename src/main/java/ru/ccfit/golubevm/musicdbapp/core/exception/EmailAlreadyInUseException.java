package ru.ccfit.golubevm.musicdbapp.core.exception;

public class EmailAlreadyInUseException extends AuthException{
    public EmailAlreadyInUseException(String email) {
        super("Email already in use: " + email);
    }
}
