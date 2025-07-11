package com.prueba.backend.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.prueba.backend.application.dto.BookDTO;
import com.prueba.backend.domain.exception.ResourceNotFoundException;
import com.prueba.backend.domain.model.Book;
import com.prueba.backend.domain.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {
    
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;

    }

    public List<BookDTO> getAll() {
        log.info("Getting all books");
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public BookDTO getById(Long id) {
 log.info("Getting book with ID: {}", id);
    Book Book = bookRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));

    return modelMapper.map(Book, BookDTO.class);
    }    


    public BookDTO update(Long id, BookDTO dto) {
        log.info("Updated user with ID: {}", id);
    Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));

                dto.setId(id);
       Book bookUpdated = modelMapper.map(dto, Book.class);

        return modelMapper.map(bookRepository.save(bookUpdated), BookDTO.class);
    }


    public BookDTO save(BookDTO dto) {
        log.info("Created Book: {}", dto);

        Book book = modelMapper.map(dto, Book.class);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookDTO.class);
    }

    public void delete(Long id) {
        log.warn("Deleting book with ID: {}", id);
        
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID:" + id));
        bookRepository.deleteById(id);
    }
}