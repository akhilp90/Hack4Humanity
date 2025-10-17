package com.example.Includify_Backend.controller;
import com.includify.entity.User;
import com.includify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){ this.userService = userService; }

    @GetMapping("/me")
    public ResponseEntity<User> me(Authentication auth){
        UUID uid = UUID.fromString((String) auth.getPrincipal());
        return ResponseEntity.ok(userService.findById(uid));
    }
}
