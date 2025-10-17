package com.example.Includify_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
    List<JobApplication> findByApplicantId(UUID applicantId);
    List<JobApplication> findByJobPostId(UUID jobPostId);
}