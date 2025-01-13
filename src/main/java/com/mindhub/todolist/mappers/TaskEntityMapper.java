package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.TaskEntityDTO.TaskEntityRequestDTO;
import com.mindhub.todolist.dtos.TaskEntityDTO.TaskEntityResponseDTO;
import com.mindhub.todolist.models.TaskEntity;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TaskEntityMapper {

    @Autowired
    private IUserEntityRepository userEntityRepository;

    public TaskEntity toEntity(TaskEntityRequestDTO taskEntityRequestDTO) {
        UserEntity userEntity = userEntityRepository.findById(taskEntityRequestDTO.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        return new TaskEntity(
                taskEntityRequestDTO.title(),
                taskEntityRequestDTO.description(),
                taskEntityRequestDTO.status(),
                userEntity);
    }

    public TaskEntityResponseDTO toTaskResponseDTO(TaskEntity taskEntity) {
        return new TaskEntityResponseDTO(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getStatus()
        );
    }

    public Set<TaskEntityResponseDTO> toTaskResponseSetDTO(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                .map(this::toTaskResponseDTO)
                .collect(Collectors.toSet());
    }
}
