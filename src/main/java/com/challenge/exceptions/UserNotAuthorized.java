package com.challenge.exceptions;

public class UserNotAuthorized extends RuntimeException{
    public UserNotAuthorized(){
        super("Somente usuários podem fazer transferência! ");
    }
    public UserNotAuthorized(String message){
        super(message);
    }
}
