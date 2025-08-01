package com.prueba.backend.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.backend.application.dto.BookDTO;
import com.prueba.backend.application.service.BookService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/books")
@Slf4j

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoints para manejar las operaciones CRUD de libros

    // Obtiene todos los libros
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        return ResponseEntity.ok(bookService.getAll());

    }


    // Obtiene un libro unico
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        BookDTO book = bookService.getById(id);
        return ResponseEntity.ok(book);
    }

    // Edita un libro unico
    @PutMapping("/{id}")
        public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.update(id,dto));
    }

    // Guarda un libro nuevo
        @PostMapping
        public ResponseEntity<BookDTO> save(@RequestBody BookDTO dto) {
            return ResponseEntity.ok(bookService.save(dto));
        }

    // Elimina un libro unico
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        }

}
