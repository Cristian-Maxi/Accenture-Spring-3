package com.mindhub.todolist.dtos.TaskEntityDTO;

import com.mindhub.todolist.enums.Status;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskEntityRequestDTO(
        @NotBlank(message = "Title no debe estar vacio")
        String title,
        @NotBlank(message = "Description no debe estar vacio")
        String description,
        @Enumerated
        Status status,
        @NotNull(message = "El ID usuario no debe ser nulo")
        Long usuarioId
) {
}