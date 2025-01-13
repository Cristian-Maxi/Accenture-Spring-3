package com.mindhub.todolist.services.impl;

import com.mindhub.todolist.dtos.TokenDTO.JWTTokenDTO;
import com.mindhub.todolist.dtos.UserEntityDTO.DatosAutenticacionUsuario;
import com.mindhub.todolist.services.IAutenticationService;
import com.mindhub.todolist.services.ITokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutenticationServiceImpl implements IAutenticationService {

    private final AuthenticationManager authenticationManager;
    private final ITokenService tokenService;

    public AutenticationServiceImpl(AuthenticationManager authenticationManager, ITokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public JWTTokenDTO autenticar(DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.getEmail(), datosAutenticacionUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenService.generateToken(authentication);
        return new JWTTokenDTO(token);
    }
}
