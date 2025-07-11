package com.prueba.backend.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.prueba.backend.domain.model.Loan;
import com.prueba.backend.domain.repository.LoanRepository;

@Repository
public class JpaLoanRepository implements LoanRepository {

    private final SpringDataLoanRepository repo;

    public JpaLoanRepository(SpringDataLoanRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Loan> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return repo.findById(id);
    }
    
    @Override
    public Loan save(Loan loan) {
        return repo.save(loan);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsByBookIdAndReturnedFalse(Long bookId) {
        return repo.existsByBookIdAndReturnedFalse(bookId);
    }
}