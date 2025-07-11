package com.prueba.backend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.backend.domain.model.Loan;

@Repository
public interface SpringDataLoanRepository extends JpaRepository<Loan, Long> {
        boolean existsByBookIdAndReturnedFalse(Long bookId);
}
