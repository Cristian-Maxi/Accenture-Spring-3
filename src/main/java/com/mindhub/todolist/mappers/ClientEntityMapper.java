package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.ClientEntityDTO.ClientEntityRequestDTO;
import com.mindhub.todolist.dtos.ClientEntityDTO.ClientEntityResponseDTO;
import com.mindhub.todolist.models.ClientEntity;
import com.mindhub.todolist.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapper {

    public ClientEntity toEntity(ClientEntityRequestDTO clientEntityRequestDTO) {
        return new ClientEntity(
                clientEntityRequestDTO.name(),
                clientEntityRequestDTO.lastname(),
                new UserEntity(
                        clientEntityRequestDTO.user().getEmail()
                )
        );
    }

    public ClientEntityResponseDTO toClientResponseDTO(ClientEntity clientEntity) {
        return new ClientEntityResponseDTO(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getLastname()
        );
    }

}