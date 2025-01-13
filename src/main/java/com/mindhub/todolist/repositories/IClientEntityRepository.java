package com.mindhub.todolist.repositories;

import com.mindhub.todolist.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientEntityRepository extends JpaRepository<ClientEntity, Long> {
}
