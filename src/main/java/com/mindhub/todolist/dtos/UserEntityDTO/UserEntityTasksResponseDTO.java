package com.mindhub.todolist.dtos.UserEntityDTO;

import com.mindhub.todolist.dtos.TaskEntityDTO.TaskEntityResponseDTO;
import com.mindhub.todolist.enums.RoleEnum;

import java.util.Set;

public record UserEntityTasksResponseDTO(
        Long id,
        String username,
        RoleEnum rol,
        Set<TaskEntityResponseDTO> taskEntitySet
) {
}
