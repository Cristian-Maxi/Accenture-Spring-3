package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.ApiResponseDTO;
import com.mindhub.todolist.dtos.ClientEntityDTO.*;
import com.mindhub.todolist.exceptions.ApplicationException;
import com.mindhub.todolist.services.IClientEntityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientEntityController {

    @Autowired
    private IClientEntityService clientEntityService;

    @PostMapping("/register")
    @Operation(summary = "Se registra un cliente")
    public ResponseEntity<ClientEntityResponseDTO> createClientEntity(@Valid @RequestBody ClientEntityRequestDTO clientEntityRequestDTO) {
        try{
            ClientEntityResponseDTO userEntityResponseDTO = clientEntityService.saveClientEntity(clientEntityRequestDTO);
            return new ResponseEntity<>(userEntityResponseDTO, HttpStatus.CREATED);
        } catch (ApplicationException e) {
            throw new ApplicationException(" Ha ocurrido un error en el campo " + e.getCampo() + ", Descripcion: "+e.getMessage());
        }
    }

    @PatchMapping("/update")
    @Operation(summary = "Se actualiza un cliente en particular")
    public ResponseEntity<ApiResponseDTO<ClientEntityResponseDTO>> updateClientEntity(@Valid @RequestBody ClientEntityUpdateDTO userEntityUpdateDTO) {
        ClientEntityResponseDTO userEntityResponseDTO = clientEntityService.update(userEntityUpdateDTO);
        String message = "Cliente Actualizado";
        return new ResponseEntity<>(new ApiResponseDTO<>(true, message, userEntityResponseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Se elimina un cliente en particular")
    public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
        clientEntityService.delete(id);
        String message = "Cliente Eliminado exitosamente";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/existUserEntity/{id}")
    @Operation(summary = "Comprueba si un cliente existe en la base de datos")
    public ResponseEntity<?> existClientEntity(@PathVariable Long id) {
        if(clientEntityService.existById(id)) {
            String message = "El cliente existe en la base de datos";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            String message = "El cliente no existe en la base de datos";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
