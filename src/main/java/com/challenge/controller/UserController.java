package com.challenge.controller;


import com.challenge.request.Transfer;
import com.challenge.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/transfer")
    @Operation(summary = "Transferencia", description = "End point para transferir dinheiro de uma carteira para outra" )
    @ApiResponse(responseCode = "200", description = "Transferencia realizada com sucesso!")
    @ApiResponse(responseCode = "403", description = "Usuario não autorizado!")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer){
        try{
            return ResponseEntity.ok(userService.transfer(transfer));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Erro de autenticação: " + e.getMessage());
        }
    }
}