package com.example.Includify_Backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    @Email @NotBlank public String email;
    @NotBlank public String password;
}