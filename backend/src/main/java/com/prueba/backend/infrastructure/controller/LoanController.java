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

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll() {
        return ResponseEntity.ok(loanService.getAll());
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id) {
    loanService.markAsReturned(id);
    return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<LoanResponseDTO> save(@RequestBody LoanRequestDTO dto) {
        return ResponseEntity.ok(loanService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}