package com.challenge.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(){
        super("User not Found!");
    }
    public UserNotFound(String message){
        super(message);
    }
}
