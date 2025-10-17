package com.example.Includify_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, UUID> {
    List<SavedJob> findByUserId(UUID userId);
    Optional<SavedJob> findByUserIdAndJobPostId(UUID userId, UUID jobPostId);
}