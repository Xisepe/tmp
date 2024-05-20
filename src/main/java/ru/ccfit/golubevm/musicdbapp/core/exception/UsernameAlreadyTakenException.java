package ru.ccfit.golubevm.musicdbapp.core.exception;

public class UsernameAlreadyTakenException extends AuthException{
    public UsernameAlreadyTakenException(String username) {
        super("Username already taken: " + username);
    }
}
