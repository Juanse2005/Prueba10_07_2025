package com.prueba.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.prueba.backend.domain.model.Loan;

public interface LoanRepository {
    List<Loan> findAll();
    Optional<Loan> findById(Long id);
    Loan save(Loan loan);
    void deleteById(Long id);

    boolean existsByBookIdAndReturnedFalse(Long bookId);
}
