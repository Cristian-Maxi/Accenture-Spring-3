package com.mindhub.todolist.services.impl;

import com.mindhub.todolist.dtos.AdminEntityDTO.*;
import com.mindhub.todolist.enums.RoleEnum;
import com.mindhub.todolist.exceptions.ApplicationException;
import com.mindhub.todolist.mappers.AdminEntityMapper;
import com.mindhub.todolist.models.AdminEntity;
import com.mindhub.todolist.repositories.IAdminEntityRepository;
import com.mindhub.todolist.repositories.IUserEntityRepository;
import com.mindhub.todolist.services.IAdminEntityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminEntityServiceImpl implements IAdminEntityService {

    @Autowired
    private IAdminEntityRepository adminEntityRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Autowired
    private AdminEntityMapper adminEntityMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AdminEntityResponseDTO savedminEntity(AdminEntityRequestDTO adminEntityRequestDTO) {
        if (userEntityRepository.existsByEmail(adminEntityRequestDTO.user().getEmail())) {
            throw new ApplicationException("email", "El email ya existe en la base de datos");
        }
        String encodedPassword = passwordEncoder.encode(adminEntityRequestDTO.user().getPassword());
        AdminEntity adminEntity = adminEntityMapper.toEntity(adminEntityRequestDTO);
        adminEntity.getUser().setPassword(encodedPassword);
        adminEntity.getUser().setRol(RoleEnum.ADMIN);
        //adminEntityRepository.save(adminEntity);
        adminEntityRepository.save((adminEntity));
        return adminEntityMapper.toAdminResponseDTO(adminEntity);
    }

    @Override
    public AdminEntityResponseDTO update(AdminEntityUpdateDTO adminEntityUpdateDTO) {
        AdminEntity adminEntity = adminEntityRepository.findById(adminEntityUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("El ID del usuario no encontrado"));
        if(adminEntityUpdateDTO.name() != null && !adminEntityUpdateDTO.name().isBlank()) {
            adminEntity.setName(adminEntityUpdateDTO.name());
        }
        if(adminEntityUpdateDTO.lastname() != null && !adminEntityUpdateDTO.lastname().isBlank()) {
            adminEntity.setLastname(adminEntityUpdateDTO.lastname());
        }
        adminEntityRepository.save(adminEntity);
        return adminEntityMapper.toAdminResponseDTO(adminEntity);
    }

    @Override
    public void delete(Long id) {
        AdminEntity adminEntity = adminEntityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        adminEntityRepository.delete(adminEntity);
    }

    @Override
    public boolean existById(Long id) {
        return adminEntityRepository.existsById(id);
    }
}
