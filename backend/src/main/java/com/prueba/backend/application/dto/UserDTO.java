package com.prueba.backend.application.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id_user;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
