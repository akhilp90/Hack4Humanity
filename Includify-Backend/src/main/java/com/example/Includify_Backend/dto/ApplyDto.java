package com.example.Includify_Backend.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class ApplyDto {
    @NotNull public UUID jobId;
    public String coverLetter;
}