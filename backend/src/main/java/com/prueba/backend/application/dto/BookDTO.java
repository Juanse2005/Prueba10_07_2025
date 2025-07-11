package com.prueba.backend.application.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String writer;
    private String gender;
}
