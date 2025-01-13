package com.mindhub.todolist.repositories;

import com.mindhub.todolist.models.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminEntityRepository extends JpaRepository<AdminEntity, Long> {
}
