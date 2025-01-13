package com.mindhub.todolist.services.impl;

import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityResposeDTO;
import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityTasksResponseDTO;
import com.mindhub.todolist.mappers.TaskEntityMapper;
import com.mindhub.todolist.mappers.UserEntityMapper;
import com.mindhub.todolist.models.TaskEntity;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.ITaskEntityRepository;
import com.mindhub.todolist.repositories.IUserEntityRepository;
import com.mindhub.todolist.services.IUserEntityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserEntityServiceImpl implements IUserEntityService {

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Autowired
    private ITaskEntityRepository taskEntityRepository;

    @Autowired
    private TaskEntityMapper taskEntityMapper;

    @Autowired
    private UserEntityMapper userEntityMapper;


    @Override
    public List<UserEntityResposeDTO> getAll() {
        List<UserEntity> userEntities = userEntityRepository.findAll();
        return userEntityMapper.toResponseListDTO(userEntities);
    }

    @Override
    @Transactional
    public UserEntityTasksResponseDTO userTasks(Long id) {
        UserEntity userEntity = userEntityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        List<TaskEntity> taskEntity = taskEntityRepository.findByUserEntityId(id);
        if (taskEntity.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron tareas para el usuario con ID: " + id);
        }
        return new UserEntityTasksResponseDTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getRol(),
                taskEntityMapper.toTaskResponseSetDTO(taskEntity)
        );
    }
}