package com.mindhub.todolist.repositories;

import com.mindhub.todolist.models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITaskEntityRepository extends JpaRepository<TaskEntity, Long> {
    Long countByUserEntityId(Long id);
    List<TaskEntity> findByUserEntityId(Long id);
}