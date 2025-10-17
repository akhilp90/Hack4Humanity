package com.example.Includify_Backend.service;


import com.includify.entity.User;
import com.includify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    public User findById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }
}

