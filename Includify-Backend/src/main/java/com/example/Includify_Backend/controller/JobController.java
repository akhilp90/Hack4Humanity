package com.example.Includify_Backend.controller;


import com.includify.dto.*;
import com.includify.entity.JobPost;
import com.includify.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService){ this.jobService = jobService; }

    @GetMapping("/")
    public ResponseEntity<List<JobPost>> browseJobs(){
        return ResponseEntity.ok(jobService.browseJobs());
    }

    @PostMapping("/create")
    public ResponseEntity<JobPost> createJob(Authentication auth, @Valid @RequestBody JobPost job){
        UUID userId = UUID.fromString((String) auth.getPrincipal());
        JobPost created = jobService.createJob(userId, job);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobPost> updateJob(Authentication auth, @PathVariable UUID jobId, @RequestBody JobPost update){
        UUID userId = UUID.fromString((String) auth.getPrincipal());
        return ResponseEntity.ok(jobService.updateJob(userId, jobId, update));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJob(Authentication auth, @PathVariable UUID jobId){
        UUID userId = UUID.fromString((String) auth.getPrincipal());
        jobService.deleteJob(userId, jobId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/apply")
    public ResponseEntity<?> apply(Authentication auth, @Valid @RequestBody ApplyDto dto){
        UUID userId = UUID.fromString((String) auth.getPrincipal());
        jobService.applyToJob(userId, dto.jobId, dto.coverLetter);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save/{jobId}")
    public ResponseEntity<?> saveJob(Authentication auth, @PathVariable UUID jobId){
        UUID userId = UUID.fromString((String) auth.getPrincipal());
        jobService.saveJob(userId, jobId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/save/{jobId}")
    public ResponseEntity<?> unsaveJob(Authentication auth, @PathVariable UUID jobId){
        UUID userId = UUID.fromString((String) auth.getPrincipal());
        jobService.unsaveJob(userId, jobId);
        return ResponseEntity.noContent().build();
    }
}
