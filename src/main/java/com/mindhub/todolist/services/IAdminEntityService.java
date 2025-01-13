package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.AdminEntityDTO.*;

public interface IAdminEntityService {
    AdminEntityResponseDTO savedminEntity(AdminEntityRequestDTO adminEntityRequestDTO);
    AdminEntityResponseDTO update(AdminEntityUpdateDTO adminEntityUpdateDTO);
    void delete(Long id);
    boolean existById(Long id);
}
