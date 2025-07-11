package com.prueba.backend.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.prueba.backend.domain.model.Book;
import com.prueba.backend.domain.repository.BookRepository;

@Repository
public class JpaBookRepository implements BookRepository {

    private final SpringDataBookRepository repo;

    public JpaBookRepository(SpringDataBookRepository repo) {
        this.repo = repo;
    }

    public List<Book> findAll() {
        return repo.findAll();
    }

    public Optional<Book> findById(Long id) {
        return repo.findById(id);
    }

    public Book save(Book book) {
        return repo.save(book);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
