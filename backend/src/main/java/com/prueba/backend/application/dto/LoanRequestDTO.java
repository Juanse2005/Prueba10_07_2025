package com.prueba.backend.application.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LoanRequestDTO {
    private Long id_loan;
    private Long userId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned;
}
