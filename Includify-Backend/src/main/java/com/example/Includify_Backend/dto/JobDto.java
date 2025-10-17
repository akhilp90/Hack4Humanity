package com.example.Includify_Backend.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class JobDto {
    public UUID id;
    @NotBlank public String title;
    public String description;
    public String location;
    public Double salaryMin;
    public Double salaryMax;
    public UUID posterId;
}
