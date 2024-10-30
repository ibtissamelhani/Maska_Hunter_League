package org.youcode.maska_hunters_league.web.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("user does not exist");
    }
}
