package org.youcode.maska_hunters_league.web.exception.user;

public class UserDoesntExistException extends RuntimeException{

    public UserDoesntExistException() {
        super("user does not exist");
    }
}
