package com.mindhub.todolist.services.impl;

import com.mindhub.todolist.dtos.ClientEntityDTO.*;
import com.mindhub.todolist.enums.RoleEnum;
import com.mindhub.todolist.exceptions.ApplicationException;
import com.mindhub.todolist.mappers.ClientEntityMapper;
import com.mindhub.todolist.models.ClientEntity;
import com.mindhub.todolist.repositories.IClientEntityRepository;
import com.mindhub.todolist.repositories.ITaskEntityRepository;
import com.mindhub.todolist.repositories.IUserEntityRepository;
import com.mindhub.todolist.services.IClientEntityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientEntityServiceImpl implements IClientEntityService {

    @Autowired
    private IClientEntityRepository clientEntityRepository;

    @Autowired
    private ITaskEntityRepository taskEntityRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Autowired
    private ClientEntityMapper clientEntityMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public ClientEntityResponseDTO saveClientEntity(ClientEntityRequestDTO clientEntityRequestDTO) {
        if (userEntityRepository.existsByEmail(clientEntityRequestDTO.user().getEmail())) {
            throw new ApplicationException("email", "El email ya existe en la base de datos");
        }
        String encodedPassword = passwordEncoder.encode(clientEntityRequestDTO.user().getPassword());
        ClientEntity clientEntity = clientEntityMapper.toEntity(clientEntityRequestDTO);
        clientEntity.getUser().setPassword(encodedPassword);
        clientEntity.getUser().setRol(RoleEnum.USER);
        //userEntityRepository.save(clientEntity.getUser());
        clientEntityRepository.save((clientEntity));
        return clientEntityMapper.toClientResponseDTO(clientEntity);
    }

    @Override
    public ClientEntityResponseDTO update(ClientEntityUpdateDTO clientEntityUpdateDTO) {
        ClientEntity clientEntity = clientEntityRepository.findById(clientEntityUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("El ID del usuario no encontrado"));
        if(clientEntityUpdateDTO.name() != null && !clientEntityUpdateDTO.name().isBlank()) {
            clientEntity.setName(clientEntityUpdateDTO.name());
        }
        if(clientEntityUpdateDTO.lastname() != null && !clientEntityUpdateDTO.lastname().isBlank()) {
            clientEntity.setLastname(clientEntityUpdateDTO.lastname());
        }
        clientEntityRepository.save(clientEntity);
        return clientEntityMapper.toClientResponseDTO(clientEntity);
    }

    @Override
    public void delete(Long id) {
        ClientEntity clientEntity = clientEntityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el ID del usuario ingresado"));
        clientEntityRepository.delete(clientEntity);
    }

    @Override
    public boolean existById(Long id) {
        return clientEntityRepository.existsById(id);
    }
}