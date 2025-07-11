package com.prueba.backend.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.prueba.backend.application.dto.LoanRequestDTO;
import com.prueba.backend.application.dto.LoanResponseDTO;
import com.prueba.backend.domain.model.Book;
import com.prueba.backend.domain.model.Loan;
import com.prueba.backend.domain.model.User;
import com.prueba.backend.domain.repository.BookRepository;
import com.prueba.backend.domain.repository.LoanRepository;
import com.prueba.backend.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public LoanService(
        LoanRepository loanRepository,
        UserRepository userRepository,
        BookRepository bookRepository,
        ModelMapper modelMapper
    ) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<LoanResponseDTO> getAll() {
        log.info("Getting all loans");

        return loanRepository.findAll().stream()
                .map(loan -> {
                    LoanResponseDTO dto = modelMapper.map(loan, LoanResponseDTO.class);
                    dto.setUserId(loan.getUser().getId_user());
                    dto.setBookId(loan.getBook().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public boolean isBookCurrentlyLoaned(Long bookId) {
        return loanRepository.existsByBookIdAndReturnedFalse(bookId);
    }

    public void markAsReturned(Long id) {
    log.info("Getting loan with ID: {}", id);
    Loan loan = loanRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + id));
    loan.setReturned(true);
    loanRepository.save(loan);
}



    public LoanResponseDTO save(LoanRequestDTO dto) {
    
        if (isBookCurrentlyLoaned(dto.getBookId())) {
    throw new IllegalStateException("El libro ya está prestado");
}
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Book book = bookRepository.findById(dto.getBookId())
            .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setReturned(dto.isReturned());

        return modelMapper.map(loanRepository.save(loan), LoanResponseDTO.class);
    }

    
  
    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}
