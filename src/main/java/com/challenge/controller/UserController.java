package com.challenge.controller;


import com.challenge.model.User;
import com.challenge.request.Transfer;
import com.challenge.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer){
        return ResponseEntity.ok(userService.transfer(transfer));
    }
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

}
