package com.mindhub.todolist.dtos.UserEntityDTO;

import com.mindhub.todolist.enums.RoleEnum;

public record UserEntityResposeDTO(
        Long id,
        String email,
        RoleEnum rol
) {
}
