package com.example.Includify_Backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank public String name;
    @Email @NotBlank public String email;
    @NotBlank @Size(min=6) public String password;
    @NotBlank public String role; // JOB_SEEKER|JOB_POSTER
}
