package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.ClientEntityDTO.ClientEntityRequestDTO;
import com.mindhub.todolist.dtos.ClientEntityDTO.ClientEntityResponseDTO;
import com.mindhub.todolist.dtos.ClientEntityDTO.ClientEntityUpdateDTO;

public interface IClientEntityService {
    ClientEntityResponseDTO saveClientEntity(ClientEntityRequestDTO clientEntityRequestDTO);
    ClientEntityResponseDTO update(ClientEntityUpdateDTO clientEntityUpdateDTO);
    void delete(Long id);
    boolean existById(Long id);
}
