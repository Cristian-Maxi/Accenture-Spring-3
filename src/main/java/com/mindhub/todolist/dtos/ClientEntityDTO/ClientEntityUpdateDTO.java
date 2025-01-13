package com.mindhub.todolist.dtos.ClientEntityDTO;

import jakarta.validation.constraints.NotNull;

public record ClientEntityUpdateDTO(
        @NotNull(message = "El ID no debe ser nulo")
        Long id,
        String name,
        String lastname
) {
}
