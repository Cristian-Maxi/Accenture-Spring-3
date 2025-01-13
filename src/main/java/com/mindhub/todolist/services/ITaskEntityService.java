package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.TaskEntityDTO.*;

import java.util.Set;

public interface ITaskEntityService {
    Long countByUserEntityId(Long id);
    TaskEntityResponseDTO saveTask(TaskEntityRequestDTO taskEntityRequestDTO);
    Set<TaskEntityResponseDTO> getAll();
    TaskEntityResponseDTO update(TaskEntityUpdateDTO taskEntityUpdateDTO);
    void delete(Long id);
}