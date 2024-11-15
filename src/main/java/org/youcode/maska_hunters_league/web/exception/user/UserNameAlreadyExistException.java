package org.youcode.maska_hunters_league.web.exception.user;

public class UserNameAlreadyExistException extends RuntimeException{

    public UserNameAlreadyExistException() {
        super("Username already exists");
    }
}
