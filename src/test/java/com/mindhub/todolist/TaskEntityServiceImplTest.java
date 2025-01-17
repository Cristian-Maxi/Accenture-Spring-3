package com.mindhub.todolist;

import com.mindhub.todolist.dtos.TaskEntityDTO.*;
import com.mindhub.todolist.enums.Status;
import com.mindhub.todolist.mappers.TaskEntityMapper;
import com.mindhub.todolist.models.TaskEntity;
import com.mindhub.todolist.repositories.ITaskEntityRepository;
import com.mindhub.todolist.repositories.IUserEntityRepository;
import com.mindhub.todolist.services.impl.TaskEntityServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskEntityServiceImplTest {

    @Mock
    private ITaskEntityRepository taskEntityRepository;

    @Mock
    private IUserEntityRepository userEntityRepository;

    @Mock
    private TaskEntityMapper taskEntityMapper;

    @InjectMocks
    private TaskEntityServiceImpl taskEntityService;

    private TaskEntity mockTask;
    private TaskEntityRequestDTO mockRequestDTO;
    private TaskEntityResponseDTO mockResponseDTO;
    private TaskEntityUpdateDTO mockUpdateDTO;

    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        mockTask = new TaskEntity();
        mockTask.setId(1L);
        mockTask.setTitle("Test Task");
        mockTask.setDescription("Test Description");
        mockTask.setStatus(Status.PENDING);

        mockRequestDTO = new TaskEntityRequestDTO(
                "Test Task",
                "Test Description",
                Status.PENDING,  // Ahora usando el enum Status
                1L
        );

        mockResponseDTO = new TaskEntityResponseDTO(
                1L,
                "Test Task",
                "Test Description",
                Status.PENDING
        );

        mockUpdateDTO = new TaskEntityUpdateDTO(
                1L,
                "Updated Task",
                "Updated Description",
                Status.COMPLETED // Actualizado para usar el enum
        );
    }

    @Test
    void countByUserEntityId_ShouldReturnCount() {
        // Arrange
        Long userId = 1L;
        when(taskEntityRepository.countByUserEntityId(userId)).thenReturn(5L);

        // Act
        Long result = taskEntityService.countByUserEntityId(userId);

        // Assert
        assertEquals(5L, result);
        verify(taskEntityRepository).countByUserEntityId(userId);
    }

    @Test
    void saveTask_ShouldReturnTaskResponseDTO() {
        // Arrange
        when(taskEntityMapper.toEntity(mockRequestDTO)).thenReturn(mockTask);
        when(taskEntityRepository.save(any(TaskEntity.class))).thenReturn(mockTask);
        when(taskEntityMapper.toTaskResponseDTO(mockTask)).thenReturn(mockResponseDTO);

        // Act
        TaskEntityResponseDTO result = taskEntityService.saveTask(mockRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(mockResponseDTO.id(), result.id());
        assertEquals(mockResponseDTO.title(), result.title());
        verify(taskEntityRepository).save(any(TaskEntity.class));
    }

    @Test
    void getAll_ShouldReturnAllTasks() {
        // Arrange
        when(taskEntityRepository.findAll()).thenReturn(Arrays.asList(mockTask));
        Set<TaskEntityResponseDTO> expectedResponse = new HashSet<>(Arrays.asList(mockResponseDTO));
        when(taskEntityMapper.toTaskResponseSetDTO(any())).thenReturn(expectedResponse);

        // Act
        Set<TaskEntityResponseDTO> result = taskEntityService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(taskEntityRepository).findAll();
    }

    @Test
    void update_ShouldUpdateAndReturnTask() {
        // Arrange
        when(taskEntityRepository.findById(mockUpdateDTO.id())).thenReturn(Optional.of(mockTask));
        when(taskEntityRepository.save(any(TaskEntity.class))).thenReturn(mockTask);
        when(taskEntityMapper.toTaskResponseDTO(mockTask)).thenReturn(mockResponseDTO);

        // Act
        TaskEntityResponseDTO result = taskEntityService.update(mockUpdateDTO);

        // Assert
        assertNotNull(result);
        verify(taskEntityRepository).save(any(TaskEntity.class));
    }

    @Test
    void update_ShouldThrowEntityNotFoundException() {
        // Arrange
        when(taskEntityRepository.findById(mockUpdateDTO.id())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> taskEntityService.update(mockUpdateDTO));
    }

    @Test
    void delete_ShouldDeleteTask() {
        // Arrange
        when(taskEntityRepository.findById(1L)).thenReturn(Optional.of(mockTask));

        // Act
        taskEntityService.delete(1L);

        // Assert
        verify(taskEntityRepository).delete(mockTask);
    }

    @Test
    void delete_ShouldThrowEntityNotFoundException() {
        // Arrange
        when(taskEntityRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> taskEntityService.delete(1L));
    }
}