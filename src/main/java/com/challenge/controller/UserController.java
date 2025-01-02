package com.challenge.controller;


import com.challenge.request.Transfer;
import com.challenge.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/transfer")
    @Operation(summary = "Transferencia", description = "End point para transferir dinheiro de uma carteira para outra" )
    @ApiResponse(responseCode = "200", description = "Transferencia realizada com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro interno. Verificar nos logs.")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer){
        return ResponseEntity.ok(userService.transfer(transfer));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

}
