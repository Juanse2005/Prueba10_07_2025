package com.prueba.backend.infrastructure.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.application.dto.LoanRequestDTO;
import com.prueba.backend.application.dto.LoanResponseDTO;
import com.prueba.backend.application.service.LoanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/loans")
@Slf4j

public class LoanController {
    private final LoanService loanService;
  public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // Endpoints para manejar las operaciones CRUD de prestamos

    // Obtiene todos los prestamos
    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll() {
        return ResponseEntity.ok(loanService.getAll());
    }


    // Obtiene un prestamo unico y lo marca como devuelto
    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id) {
    loanService.markAsReturned(id);
    return ResponseEntity.ok().build();
    }

    // Crea un nuevo prestamo
    @PostMapping
    public ResponseEntity<LoanResponseDTO> save(@RequestBody LoanRequestDTO dto) {
        return ResponseEntity.ok(loanService.save(dto));
    }


    // Obtiene un prestamo unico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtiene la cantidad total de prestamos
     @GetMapping("/stats/users-with-loans")
    public ResponseEntity<Map<String, Long>> getUsersWithLoansStat() {
        long count = loanService.countUsersWithLoans();
        Map<String, Long> response = new HashMap<>();
        response.put("total", count);
        return ResponseEntity.ok(response);
    }

}