package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.ApiResponseDTO;
import com.mindhub.todolist.dtos.ClientEntityDTO.*;
import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityResposeDTO;
import com.mindhub.todolist.dtos.UserEntityDTO.UserEntityTasksResponseDTO;
import com.mindhub.todolist.exceptions.ApplicationException;
import com.mindhub.todolist.services.IUserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserEntityController {

    @Autowired
    private IUserEntityService userEntityService;

    @GetMapping("/getAll")
    @Operation(summary = "Trae todos los usuarios de la base de datos")
    public ResponseEntity<ApiResponseDTO<UserEntityResposeDTO>> getAllUsuarios() {
        try {
            List<UserEntityResposeDTO> userEntityResponseDTO = userEntityService.getAll();
            if (userEntityResponseDTO.isEmpty()) {
                return new ResponseEntity<>(new ApiResponseDTO<>(false, "No Hay Usuarios Registrados", userEntityResponseDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponseDTO<>(true, "Usuarios Registrados", userEntityResponseDTO), HttpStatus.OK);
            }
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error " + e.getMessage());
        }
    }

    @GetMapping("/findUserTasks/{id}")
    @Operation(summary = "Trae todas las tareas de un usuario en particular")
    public ResponseEntity<ApiResponseDTO<UserEntityTasksResponseDTO>> findUserTasksById(@PathVariable Long id) {
        UserEntityTasksResponseDTO userTasks = userEntityService.userTasks(id);
        String message = "Usuario y Tareas encontradas";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, userTasks), HttpStatus.OK);
    }

}
