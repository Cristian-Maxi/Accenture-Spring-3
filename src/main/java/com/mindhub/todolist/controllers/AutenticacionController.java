package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.TokenDTO.JWTTokenDTO;
import com.mindhub.todolist.dtos.UserEntityDTO.DatosAutenticacionUsuario;
import com.mindhub.todolist.services.IAutenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
public class AutenticacionController {

    @Autowired
    private IAutenticationService autenticacionService;

    @PostMapping
    @Operation(summary = "Este endpoint se encarga de la autenticacion de los usuarios")
    public ResponseEntity<JWTTokenDTO> autenticar(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        JWTTokenDTO jwtTokenDTO = autenticacionService.autenticar(datosAutenticacionUsuario);
        return ResponseEntity.ok(jwtTokenDTO);
    }

}
