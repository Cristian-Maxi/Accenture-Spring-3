package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityResposeDTO;
import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityTasksResponseDTO;

import java.util.List;

public interface IUserEntityService {
    List<UserEntityResposeDTO> getAll();
    UserEntityTasksResponseDTO userTasks(Long id);
}