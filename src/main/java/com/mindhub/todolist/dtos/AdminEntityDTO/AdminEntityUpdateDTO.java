package com.mindhub.todolist.dtos.AdminEntityDTO;

import jakarta.validation.constraints.NotNull;

public record AdminEntityUpdateDTO(
        @NotNull(message = "El ID no debe ser nulo")
        Long id,
        String name,
        String lastname
) {
}
