package org.youcode.maska_hunters_league.web.exception.user;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException() {
        super("Email already exist");
    }
}
