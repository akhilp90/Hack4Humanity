package com.example.Includify_Backend.service;

import com.includify.dto.RegisterRequest;
import com.includify.entity.User;
import com.includify.repository.UserRepository;
import com.includify.config.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    public String register(RegisterRequest r){
        if(userRepository.existsByEmail(r.email)) throw new RuntimeException("Email already taken");
        User.Role role;
        try {
            role = User.Role.valueOf(r.role);
        } catch(Exception ex){
            // Accept friendly names JOB_SEEKER or JOB_POSTER. default job seeker
            role = r.role.equalsIgnoreCase("JOB_POSTER") ? User.Role.JOB_POSTER : User.Role.JOB_SEEKER;
        }
        User u = new User(r.name, r.email, passwordEncoder.encode(r.password), role);
        userRepository.save(u);
        return jwtUtil.generateToken(u.getEmail(), u.getId().toString(), u.getRole().name());
    }
}