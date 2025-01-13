package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.ApiResponseDTO;
import com.mindhub.todolist.dtos.TaskEntityDTO.*;
import com.mindhub.todolist.exceptions.ApplicationException;
import com.mindhub.todolist.services.ITaskEntityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/task")
public class TaskEntityController {

    @Autowired
    private ITaskEntityService taskEntityService;

    @PostMapping("/create")
    @Operation(summary = "Se crea una tarea")
    public ResponseEntity<TaskEntityResponseDTO> createTask(@Valid @RequestBody TaskEntityRequestDTO taskEntityRequestDTO) {
        try {
            TaskEntityResponseDTO taskEntityResponseDTO = taskEntityService.saveTask(taskEntityRequestDTO);
            return new ResponseEntity<>(taskEntityResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    @Operation(summary = "Trae todas las tareas de la base de datos")
    public ResponseEntity<ApiResponseDTO<TaskEntityResponseDTO>> getAllUsuarios() {
        try{
            Set<TaskEntityResponseDTO> taskEntityResponseDTOS= taskEntityService.getAll();
            if(taskEntityResponseDTOS.isEmpty()) {
                return new ResponseEntity<>(new ApiResponseDTO<>(false, "No Hay Tareas Disponibles", taskEntityResponseDTOS), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseDTO<>(true, "Tareas disponibles", taskEntityResponseDTOS), HttpStatus.OK);
            }
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error " + e.getMessage());
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "Se actualiza una tarea en particular")
    public ResponseEntity<ApiResponseDTO<TaskEntityUpdateDTO>> updateTask(@Valid @RequestBody TaskEntityUpdateDTO taskEntityUpdateDTO) {
        TaskEntityResponseDTO taskEntityResponseDTO = taskEntityService.update(taskEntityUpdateDTO);
        String message = "Tarea Actualizada";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, taskEntityUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Se elimina una tarea en particular")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskEntityService.delete(id);
        String message = "Tarea Eliminada exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/countUserTasks/{id}")
    @Operation(summary = "Cuenta las tareas de un usuario por ID")
    public ResponseEntity<?> userTasksCount(@PathVariable Long id) {
        Long taskCount = taskEntityService.countByUserEntityId(id);
        String message = "El usuario con ID " + id + " tiene " + taskCount + " tareas.";
        return ResponseEntity.ok(new ApiResponseDTO<>(true, message, taskCount));
    }

}
