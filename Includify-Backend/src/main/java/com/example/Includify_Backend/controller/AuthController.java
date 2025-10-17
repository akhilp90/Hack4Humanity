package com.example.Includify_Backend.controller;


import com.includify.dto.*;
import com.includify.entity.User;
import com.includify.repository.UserRepository;
import com.includify.config.JwtUtil;
import com.includify.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest r){
        String token = authService.register(r);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req){
        var userOpt = userRepository.findByEmail(req.email);
        if(userOpt.isEmpty()) return ResponseEntity.status(401).build();
        var user = userOpt.get();
        if(!passwordEncoder.matches(req.password, user.getPassword())) return ResponseEntity.status(401).build();
        String token = jwtUtil.generateToken(user.getEmail(), user.getId().toString(), user.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

