package com.prueba.backend.infrastructure.repository;

import com.prueba.backend.domain.model.User;
import com.prueba.backend.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository repo;

    public JpaUserRepository(SpringDataUserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
