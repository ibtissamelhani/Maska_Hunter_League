package org.youcode.maska_hunters_league.web.exception.user;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
