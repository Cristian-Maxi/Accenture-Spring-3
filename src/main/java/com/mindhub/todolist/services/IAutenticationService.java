package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.TokenDTO.JWTTokenDTO;
import com.mindhub.todolist.dtos.UserEntityDTO.DatosAutenticacionUsuario;

public interface IAutenticationService {

    JWTTokenDTO autenticar(DatosAutenticacionUsuario datosAutenticacionUsuario);
}
