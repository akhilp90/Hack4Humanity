package com.example.Includify_Backend.controller;

import com.includify.entity.JobPost;
import com.includify.repository.JobPostRepository;
import com.includify.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;
    public AdminController(JobPostRepository jobPostRepository, UserRepository userRepository){
        this.jobPostRepository = jobPostRepository;
        this.userRepository = userRepository;
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable UUID id){
        JobPost job = jobPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        jobPostRepository.delete(job);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/job/{id}/deactivate")
    public ResponseEntity<JobPost> deactivateJob(@PathVariable UUID id){
        JobPost job = jobPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        job.setActive(false);
        jobPostRepository.save(job);
        return ResponseEntity.ok(job);
    }
}

