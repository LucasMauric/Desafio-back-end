package com.challenge.ExceptionController;

import com.challenge.exceptions.InsufficientBalanceException;
import com.challenge.exceptions.UserNotAuthorized;
import com.challenge.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> userNotFound(UserNotFound userNotFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getMessage());
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?>insufficientBalanceException(InsufficientBalanceException insufficientBalanceException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(insufficientBalanceException.getMessage());
    }
    @ExceptionHandler(UserNotAuthorized.class)
    public ResponseEntity<?> userNotAuthorized(UserNotAuthorized userNotAuthorized){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userNotAuthorized.getMessage());
    }



}
