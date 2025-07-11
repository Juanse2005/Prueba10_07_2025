package com.prueba.backend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.backend.domain.model.User;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {

}    

