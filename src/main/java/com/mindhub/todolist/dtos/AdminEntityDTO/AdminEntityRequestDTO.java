package com.mindhub.todolist.dtos.AdminEntityDTO;

import com.mindhub.todolist.dtos.UserEntityDTO.DatosAutenticacionUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminEntityRequestDTO(
        @NotBlank(message = "Name no debe estar vacio")
        String name,
        @NotBlank(message = "Lastname no debe estar vacio")
        String lastname,
        @Valid @NotNull
        DatosAutenticacionUsuario user
) {
}
