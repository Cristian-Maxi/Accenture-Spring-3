package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.AdminEntityDTO.*;

public interface IAdminEntityService {
    AdminEntityResponseDTO saveCAdminEntity(AdminEntityRequestDTO adminEntityRequestDTO);
    AdminEntityResponseDTO update(AdminEntityUpdateDTO adminEntityUpdateDTO);
    void delete(Long id);
    boolean existById(Long id);
}
