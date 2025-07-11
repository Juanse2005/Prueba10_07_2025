package com.prueba.backend.application.service;

import com.prueba.backend.application.dto.UserDTO;
import com.prueba.backend.domain.exception.UserNotFoundException;
import com.prueba.backend.domain.model.User;
import com.prueba.backend.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    public List<UserDTO> getAll() {
        log.info("Getting all users");
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {

    log.info("Getting user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

    return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO update(Long id, UserDTO dto){
        log.info("Updated user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

                dto.setId_user(id);
       User userUpdated = modelMapper.map(dto, User.class);

        return modelMapper.map(userRepository.save(userUpdated), UserDTO.class);
    }


    public UserDTO save(UserDTO dto) {
        log.info("Created user: {}", dto);

        User user = modelMapper.map(dto, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDTO.class);
    }

    public void delete(Long id) {
        log.warn("Deleting user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID:" + id));
        userRepository.deleteById(id);
    }
}
