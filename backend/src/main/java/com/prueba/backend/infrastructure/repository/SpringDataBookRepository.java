package com.prueba.backend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.backend.domain.model.Book;

public interface SpringDataBookRepository extends JpaRepository<Book, Long> {

}
