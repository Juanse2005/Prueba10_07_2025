package com.prueba.backend.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.application.dto.UserDTO;
import com.prueba.backend.application.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j

public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoints para manejar las operaciones CRUD de usuarios

    // Obtiene todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());

    }

    // Obtiene un usuario unico
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        UserDTO user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    // Edita un usuario unico
    @PutMapping("/{id}")
        public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.update(id,dto));
        }

    // Guarda un usuario nuevo
    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.save(dto));
    }

    // Elimina un usuario unico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
