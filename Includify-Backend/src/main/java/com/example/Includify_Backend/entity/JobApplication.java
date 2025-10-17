package com.example.Includify_Backend.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "job_applications",
        uniqueConstraints = @UniqueConstraint(columnNames = {"applicant_id","job_post_id"}))
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobPost jobPost;

    @Column(length=2000)
    private String coverLetter;

    private Instant appliedAt = Instant.now();

    public JobApplication(){}
    // getters/setters...
}