package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.AdminEntityDTO.*;
import com.mindhub.todolist.models.AdminEntity;
import com.mindhub.todolist.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminEntityMapper {

    public AdminEntity toEntity(AdminEntityRequestDTO adminEntityRequestDTO) {
        return new AdminEntity(
                adminEntityRequestDTO.name(),
                adminEntityRequestDTO.lastname(),
                new UserEntity(
                        adminEntityRequestDTO.user().getEmail()
                )
        );
    }

    public AdminEntityResponseDTO toAdminResponseDTO(AdminEntity adminEntity) {
        return new AdminEntityResponseDTO(
                adminEntity.getId(),
                adminEntity.getName(),
                adminEntity.getLastname()
        );
    }
}
