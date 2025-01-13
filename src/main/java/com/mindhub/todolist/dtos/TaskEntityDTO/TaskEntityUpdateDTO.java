package com.mindhub.todolist.dtos.TaskEntityDTO;

import com.mindhub.todolist.enums.Status;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record TaskEntityUpdateDTO(
        @NotNull(message = "El ID no debe ser nulo")
        Long id,
        String title,
        String description,
        @Enumerated
        Status status
) {
}
