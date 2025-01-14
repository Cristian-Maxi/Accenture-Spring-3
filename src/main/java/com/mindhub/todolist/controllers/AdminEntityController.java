package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.AdminEntityDTO.*;
import com.mindhub.todolist.dtos.ApiResponseDTO;
import com.mindhub.todolist.exceptions.ApplicationException;
import com.mindhub.todolist.services.IAdminEntityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminEntityController {
    @Autowired
    private IAdminEntityService adminEntityService;

    @PostMapping("/register")
    @Operation(summary = "Se registra un admin")
    public ResponseEntity<AdminEntityResponseDTO> createAdminEntity(@Valid @RequestBody AdminEntityRequestDTO adminEntityRequestDTO) {
        try{
            AdminEntityResponseDTO adminEntityResponseDTO = adminEntityService.savedminEntity(adminEntityRequestDTO);
            return new ResponseEntity<>(adminEntityResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error en el campo " + e.getCampo() + ", Descripcion: "+e.getMessage());
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "Se actualiza un admin en particular")
    public ResponseEntity<ApiResponseDTO<AdminEntityResponseDTO>> updateAdminEntity(@Valid @RequestBody AdminEntityUpdateDTO adminEntityUpdateDTO) {
        AdminEntityResponseDTO adminEntityResponseDTO = adminEntityService.update(adminEntityUpdateDTO);
        String message = "Admin Actualizado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, adminEntityResponseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Se elimina un Admin en particular")
    public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
        adminEntityService.delete(id);
        String message = "Admin Eliminado exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/existUserEntity/{id}")
    @Operation(summary = "Comprueba si un admin existe en la base de datos")
    public ResponseEntity<?> existAdminEntity(@PathVariable Long id) {
        if(adminEntityService.existById(id)) {
            String message = "El Admin existe en la base de datos";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "El Admin no existe en la base de datos";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
