package com.mindhub.todolist.dtos.TaskEntityDTO;

import com.mindhub.todolist.enums.Status;

public record TaskEntityResponseDTO(
        Long id,
        String title,
        String description,
        Status status
) {
}
