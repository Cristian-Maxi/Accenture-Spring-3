package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityResposeDTO;
import com.mindhub.todolist.models.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper {
    public UserEntityResposeDTO toResponseDTO(UserEntity userEntitie) {
        return new UserEntityResposeDTO(
                userEntitie.getId(),
                userEntitie.getEmail(),
                userEntitie.getRol()
        );
    }

    public List<UserEntityResposeDTO> toResponseListDTO(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
