package com.prueba.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.prueba.backend.domain.model.Book;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}
