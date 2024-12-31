package com.challenge.controller;

import com.challenge.model.DTO.Login;
import com.challenge.model.DTO.UserDTO;
import com.challenge.model.User;
import com.challenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "End point para criar um novo usuário.")
    @ApiResponse(responseCode = "201", description = "Usuario registrado com sucesso!")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user){
        var userCreate = userService.create(user);
        return ResponseEntity.ok().body(new UserDTO(userCreate));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "End point para efetuar o login de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso!")
    public ResponseEntity<?> login(@RequestBody @Valid Login login){
        var authentication = new UsernamePasswordAuthenticationToken(login.username(),login.password());
        var auth = authenticationManager.authenticate(authentication);

        return ResponseEntity.ok().build();
    }
}
