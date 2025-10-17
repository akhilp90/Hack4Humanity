package com.example.Includify_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JobPostRepository extends JpaRepository<JobPost, UUID> {
    List<JobPost> findByActiveTrue();
    List<JobPost> findByPosterId(UUID posterId);